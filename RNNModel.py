import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import SimpleRNN, Dense

data = np.array([[i+j for j in range(5)] for i in range(100)])
targets = np.array([i+5 for i in range(100)])

X = data.reshape((data.shape[0], data.shape[1], 1))
y = targets

model = Sequential()
model.add(SimpleRNN(50, activation='relu', input_shape=(X.shape[1], X.shape[2])))
model.add(Dense(1))

model.compile(optimizer='adam', loss='mse')
model.fit(X, y, epochs=200, batch_size=10, verbose=0)

test_input = np.array([100, 101, 102, 103, 104]).reshape((1, 5, 1))
predicted = model.predict(test_input, verbose=0)
print("Predicted value:", predicted[0][0])
