#include <iostream>
#include <vector>
#include <set>

int main() {
    int n;
    std::cout << "Enter the number of elements: ";
    std::cin >> n;

    std::vector<int> arr(n);

    std::cout << "Enter " << n << " elements: ";
    for (int i = 0; i < n; ++i) {
        std::cin >> arr[i];
    }

    std::set<int> uniqueElements;
    for (int i : arr) {
        uniqueElements.insert(i);
    }

    std::cout << "Unique elements in the array: ";
    for (int element : uniqueElements) {
        std::cout << element << " ";
    }
    std::cout << std::endl;

    return 0;
}
