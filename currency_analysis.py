# 1_currency_analysis.py
import pandas as pd

data = {
    "Currency": ["USD", "EUR", "JPY", "INR"],
    "Rate_to_INR": [83.2, 90.3, 0.56, 1]
}

df = pd.DataFrame(data)
print("💱 Currency Rates (Base: INR)")
print(df)

df["1_Lakh_INR_in_Currency"] = 100000 / df["Rate_to_INR"]
print("\n💰 Conversion for ₹1 Lakh:")
print(df)
