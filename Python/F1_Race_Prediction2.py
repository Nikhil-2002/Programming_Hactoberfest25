# -*- coding: utf-8 -*-
"""Enhanced F1 Race Winner Prediction Model"""

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report, roc_auc_score
from sklearn.preprocessing import StandardScaler
import warnings
warnings.filterwarnings('ignore')

# Load datasets
print("Loading datasets...")
drivers = pd.read_csv("/content/drivers.csv")
constructor = pd.read_csv("/content/constructors.csv")
driver_standings = pd.read_csv("/content/driver_standings.csv")
constructor_standings = pd.read_csv("/content/constructor_standings.csv")
results = pd.read_csv("/content/results.csv")
constructor_results = pd.read_csv("/content/constructor_results.csv")

print(f"Total races in dataset: {results['raceId'].nunique()}")
print(f"Total drivers: {drivers.shape[0]}")
print(f"Total constructors: {constructor.shape[0]}")

# ============================================
# FEATURE ENGINEERING
# ============================================

print("\nCreating enhanced features...")

# Merge driver and constructor info
data = results.merge(drivers, on="driverId", how="left")
data = data.merge(constructor, on="constructorId", how="left")

# 1. Average points per race (historical performance)
driver_points_avg = results.groupby('driverId')['points'].mean().to_dict()
constructor_points_avg = results.groupby('constructorId')['points'].mean().to_dict()
data['driver_points_avg'] = data['driverId'].map(driver_points_avg)
data['constructor_points_avg'] = data['constructorId'].map(constructor_points_avg)

# 2. Recent form (last 5 races average points)
def get_recent_form(df, n_races=5):
    recent_form = {}
    for driver_id in df['driverId'].unique():
        driver_data = df[df['driverId'] == driver_id].sort_values('raceId')
        if len(driver_data) >= n_races:
            recent_form[driver_id] = driver_data.tail(n_races)['points'].mean()
        else:
            recent_form[driver_id] = driver_data['points'].mean()
    return recent_form

driver_recent_form = get_recent_form(results, n_races=5)
data['driver_recent_form'] = data['driverId'].map(driver_recent_form)

# 3. Win rate (percentage of races won)
driver_wins = results[results['positionOrder'] == 1].groupby('driverId').size().to_dict()
driver_races = results.groupby('driverId').size().to_dict()
driver_win_rate = {k: driver_wins.get(k, 0) / driver_races[k] for k in driver_races}
data['driver_win_rate'] = data['driverId'].map(driver_win_rate)

# 4. Podium rate (top 3 finishes)
driver_podiums = results[results['positionOrder'] <= 3].groupby('driverId').size().to_dict()
driver_podium_rate = {k: driver_podiums.get(k, 0) / driver_races[k] for k in driver_races}
data['driver_podium_rate'] = data['driverId'].map(driver_podium_rate)

# 5. DNF rate (Did Not Finish - reliability indicator)
driver_dnf = results[results['positionOrder'].isna()].groupby('driverId').size().to_dict()
driver_dnf_rate = {k: driver_dnf.get(k, 0) / driver_races[k] for k in driver_races}
data['driver_dnf_rate'] = data['driverId'].map(driver_dnf_rate)

# 6. Constructor win rate
constructor_wins = results[results['positionOrder'] == 1].groupby('constructorId').size().to_dict()
constructor_races = results.groupby('constructorId').size().to_dict()
constructor_win_rate = {k: constructor_wins.get(k, 0) / constructor_races[k] for k in constructor_races}
data['constructor_win_rate'] = data['constructorId'].map(constructor_win_rate)

# 7. Grid position advantage (qualifying performance)
data['grid_position'] = data['grid']

# 8. Circuit-specific performance (average position at each circuit)
circuit_performance = results.groupby(['driverId', 'raceId'])['positionOrder'].mean().reset_index()
circuit_avg = circuit_performance.groupby('driverId')['positionOrder'].mean().to_dict()
data['circuit_avg_position'] = data['driverId'].map(circuit_avg)

# 9. Team strength indicator
data['team_strength'] = data['constructor_points_avg'] / (data['driver_points_avg'] + 1)

# 10. Grid vs Average finish (consistency metric)
avg_finish = results.groupby('driverId')['positionOrder'].mean().to_dict()
data['avg_finish_position'] = data['driverId'].map(avg_finish)
data['grid_vs_finish'] = data['grid_position'] - data['avg_finish_position']

# Fill missing values
print("Handling missing values...")
numeric_columns = ['driver_points_avg', 'constructor_points_avg', 'driver_recent_form', 
                   'driver_win_rate', 'driver_podium_rate', 'driver_dnf_rate',
                   'constructor_win_rate', 'circuit_avg_position', 'team_strength',
                   'avg_finish_position', 'grid_vs_finish']

for col in numeric_columns:
    data[col] = data[col].fillna(0)

# Target variable: 1 if driver won, 0 otherwise
data['target'] = (data['positionOrder'] == 1).astype(int)

print(f"\nTotal wins in dataset: {data['target'].sum()}")
print(f"Win percentage: {data['target'].mean() * 100:.2f}%")

# ============================================
# MODEL TRAINING
# ============================================

# Select features for training
features = [
    'grid_position',
    'driver_points_avg',
    'constructor_points_avg',
    'driver_recent_form',
    'driver_win_rate',
    'driver_podium_rate',
    'driver_dnf_rate',
    'constructor_win_rate',
    'circuit_avg_position',
    'team_strength',
    'grid_vs_finish'
]

X = data[features]
y = data['target']

# Split data
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42, stratify=y)

print(f"\nTraining set size: {len(X_train)}")
print(f"Test set size: {len(X_test)}")

# Train multiple models
print("\n" + "="*50)
print("TRAINING MODELS")
print("="*50)

# Model 1: Random Forest
print("\n1. Random Forest Classifier...")
rf_model = RandomForestClassifier(n_estimators=200, max_depth=10, min_samples_split=5, random_state=42)
rf_model.fit(X_train, y_train)
rf_pred = rf_model.predict(X_test)
rf_prob = rf_model.predict_proba(X_test)[:, 1]
rf_accuracy = accuracy_score(y_test, rf_pred)
rf_auc = roc_auc_score(y_test, rf_prob)

print(f"Random Forest Accuracy: {rf_accuracy:.4f}")
print(f"Random Forest AUC-ROC: {rf_auc:.4f}")

# Model 2: Gradient Boosting
print("\n2. Gradient Boosting Classifier...")
gb_model = GradientBoostingClassifier(n_estimators=200, max_depth=5, learning_rate=0.1, random_state=42)
gb_model.fit(X_train, y_train)
gb_pred = gb_model.predict(X_test)
gb_prob = gb_model.predict_proba(X_test)[:, 1]
gb_accuracy = accuracy_score(y_test, gb_pred)
gb_auc = roc_auc_score(y_test, gb_prob)

print(f"Gradient Boosting Accuracy: {gb_accuracy:.4f}")
print(f"Gradient Boosting AUC-ROC: {gb_auc:.4f}")

# Select best model
if gb_auc > rf_auc:
    best_model = gb_model
    model_name = "Gradient Boosting"
    best_accuracy = gb_accuracy
    best_auc = gb_auc
else:
    best_model = rf_model
    model_name = "Random Forest"
    best_accuracy = rf_accuracy
    best_auc = rf_auc

print(f"\n✓ Best Model: {model_name}")
print(f"  Accuracy: {best_accuracy:.4f}")
print(f"  AUC-ROC: {best_auc:.4f}")

# Cross-validation
print("\nPerforming 5-fold cross-validation...")
cv_scores = cross_val_score(best_model, X, y, cv=5, scoring='roc_auc')
print(f"Cross-validation AUC-ROC: {cv_scores.mean():.4f} (+/- {cv_scores.std():.4f})")

# ============================================
# MODEL EVALUATION
# ============================================

print("\n" + "="*50)
print("MODEL EVALUATION")
print("="*50)

# Confusion Matrix
if model_name == "Gradient Boosting":
    cm = confusion_matrix(y_test, gb_pred)
else:
    cm = confusion_matrix(y_test, rf_pred)

print("\nConfusion Matrix:")
print(cm)

# Classification Report
if model_name == "Gradient Boosting":
    print("\nClassification Report:")
    print(classification_report(y_test, gb_pred, target_names=['No Win', 'Win']))
else:
    print("\nClassification Report:")
    print(classification_report(y_test, rf_pred, target_names=['No Win', 'Win']))

# Feature Importance
print("\n" + "="*50)
print("FEATURE IMPORTANCE")
print("="*50)

feature_importance = pd.DataFrame({
    'feature': features,
    'importance': best_model.feature_importances_
}).sort_values('importance', ascending=False)

print("\nTop 5 Most Important Features:")
for idx, row in feature_importance.head(5).iterrows():
    print(f"{row['feature']}: {row['importance']:.4f}")

# ============================================
# VISUALIZATION
# ============================================

print("\nGenerating visualizations...")

# Plot 1: Feature Importance
plt.figure(figsize=(12, 6))
plt.barh(feature_importance['feature'], feature_importance['importance'], color='steelblue')
plt.xlabel('Importance Score', fontsize=12)
plt.ylabel('Features', fontsize=12)
plt.title(f'Feature Importance - {model_name}', fontsize=14, fontweight='bold')
plt.tight_layout()
plt.show()

# Plot 2: Confusion Matrix Heatmap
plt.figure(figsize=(8, 6))
sns.heatmap(cm, annot=True, fmt='d', cmap='Blues', cbar=True,
            xticklabels=['No Win', 'Win'],
            yticklabels=['No Win', 'Win'])
plt.ylabel('Actual', fontsize=12)
plt.xlabel('Predicted', fontsize=12)
plt.title('Confusion Matrix Heatmap', fontsize=14, fontweight='bold')
plt.tight_layout()
plt.show()

# ============================================
# FUTURE RACE PREDICTION
# ============================================

print("\n" + "="*50)
print("FUTURE RACE PREDICTION")
print("="*50)

# Example future race setup
future_race = pd.DataFrame({
    'driverId': [1, 4, 20, 844, 830, 832, 846, 847, 815, 807],  # Top 10 drivers
    'grid_position': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
})

# Map driver names
driver_names = drivers.set_index('driverId')['surname'].to_dict()
future_race['driver_name'] = future_race['driverId'].map(driver_names)

# Map latest constructor for each driver
latest_race_per_driver = results.sort_values('raceId', ascending=False).groupby('driverId').first().reset_index()
driver_constructor_map = latest_race_per_driver.set_index('driverId')['constructorId'].to_dict()
future_race['constructorId'] = future_race['driverId'].map(driver_constructor_map)

# Map constructor names
constructor_names = constructor.set_index('constructorId')['name'].to_dict()
future_race['constructor_name'] = future_race['constructorId'].map(constructor_names)

# Map all features
future_race['driver_points_avg'] = future_race['driverId'].map(driver_points_avg)
future_race['constructor_points_avg'] = future_race['constructorId'].map(constructor_points_avg)
future_race['driver_recent_form'] = future_race['driverId'].map(driver_recent_form)
future_race['driver_win_rate'] = future_race['driverId'].map(driver_win_rate)
future_race['driver_podium_rate'] = future_race['driverId'].map(driver_podium_rate)
future_race['driver_dnf_rate'] = future_race['driverId'].map(driver_dnf_rate)
future_race['constructor_win_rate'] = future_race['constructorId'].map(constructor_win_rate)
future_race['circuit_avg_position'] = future_race['driverId'].map(circuit_avg)
future_race['avg_finish_position'] = future_race['driverId'].map(avg_finish)

# Calculate derived features
future_race['team_strength'] = future_race['constructor_points_avg'] / (future_race['driver_points_avg'] + 1)
future_race['grid_vs_finish'] = future_race['grid_position'] - future_race['avg_finish_position']

# Fill any missing values
for col in features:
    if col in future_race.columns:
        future_race[col] = future_race[col].fillna(0)

# Make predictions
X_future = future_race[features]
future_race['win_probability'] = best_model.predict_proba(X_future)[:, 1]

# Sort by win probability
future_race_sorted = future_race.sort_values('win_probability', ascending=False)

# Display top 5 predictions
print("\n🏁 TOP 5 PREDICTED RACE WINNERS 🏁\n")
top5 = future_race_sorted.head(5)

for idx, (i, row) in enumerate(top5.iterrows(), 1):
    print(f"{'='*60}")
    print(f"POSITION {idx}: {row['driver_name']} ({row['constructor_name']})")
    print(f"{'='*60}")
    print(f"  Starting Grid: P{int(row['grid_position'])}")
    print(f"  Win Probability: {row['win_probability']:.2%}")
    print(f"  Driver Avg Points: {row['driver_points_avg']:.2f}")
    print(f"  Recent Form (Last 5): {row['driver_recent_form']:.2f}")
    print(f"  Win Rate: {row['driver_win_rate']:.2%}")
    print(f"  Podium Rate: {row['driver_podium_rate']:.2%}")
    print()

# Plot predictions
plt.figure(figsize=(14, 7))
bars = plt.bar(range(len(top5)), top5['win_probability'], 
               color=['gold', 'silver', '#CD7F32', 'steelblue', 'lightblue'])

# Add driver names and probabilities
for idx, (bar, (i, row)) in enumerate(zip(bars, top5.iterrows())):
    height = bar.get_height()
    plt.text(bar.get_x() + bar.get_width()/2.0, height + 0.01, 
             f"{height:.2%}", ha='center', va='bottom', fontsize=11, fontweight='bold')
    plt.text(bar.get_x() + bar.get_width()/2.0, -0.03, 
             row['driver_name'], ha='center', va='top', fontsize=10, rotation=0)

plt.ylabel('Win Probability', fontsize=13, fontweight='bold')
plt.xlabel('Driver', fontsize=13, fontweight='bold')
plt.title('🏎️ F1 Race Winner Prediction - Top 5 Drivers', fontsize=15, fontweight='bold', pad=20)
plt.ylim(0, max(top5['win_probability']) * 1.2)
plt.xticks([])
plt.grid(axis='y', alpha=0.3, linestyle='--')
plt.tight_layout()
plt.show()

# Full predictions table
print("\n" + "="*50)
print("COMPLETE PREDICTION TABLE")
print("="*50)
print(future_race_sorted[['driver_name', 'constructor_name', 'grid_position', 
                          'win_probability', 'driver_recent_form', 'driver_win_rate']].to_string(index=False))

print("\n✅ Model training and prediction complete!")
print(f"📊 Best Model Used: {model_name}")
print(f"🎯 Model Accuracy: {best_accuracy:.2%}")
print(f"📈 AUC-ROC Score: {best_auc:.4f}")
