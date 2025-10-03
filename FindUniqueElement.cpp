#include <iostream>
#include <vector>
#include <unordered_set>

int main() {
    int n;
    std::cout << "Enter the number of elements: ";
    std::cin >> n;

    std::vector<int> arr(n);
    std::cout << "Enter " << n << " elements: ";
    for (int& element : arr) {
        std::cin >> element; // Use reference for efficiency
    }

    // Use unordered_set for faster average case O(1) insertions
    std::unordered_set<int> uniqueElements(arr.begin(), arr.end());

    std::cout << "Unique elements in the array: ";
    for (const int& element : uniqueElements) {
        std::cout << element << " ";
    }
    std::cout << std::endl;

    return 0;
}
