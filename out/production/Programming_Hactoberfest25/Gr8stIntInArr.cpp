#include <iostream>
using namespace std;

int findLargestElement(int arr[], int size) {
    if (size <= 0) {
        // Ha the case of an empty array or invalid size
        return -1;
    }

    int largest = arr[0];  // Assuming the first element is the largest

    for (int i = 1; i < size; i++) {
        if (arr[i] > largest) {
            largest = arr[i]; // Updating largest if current element is greater
        }
    }

    return largest;
}

int main() {
    int size;

    cout << "Enter the size of the array: ";
    cin >> size;

    if (size <= 0) {
        cout << "Invalid array size." <<endl;
        return 1;
    }

    int arr[size];

    cout << "Enter the elements of the array, separated by spaces:" <<endl;
    for (int i = 0; i < size; i++) {
        cin >> arr[i];
    }

    int largest = findLargestElement(arr, size);

    if (largest != -1) {
        cout << "The largest element in the array is: " << largest <<endl;
    } else {
        cout << "The array is empty or has an invalid size." <<endl;
    }

    return 0;
}
