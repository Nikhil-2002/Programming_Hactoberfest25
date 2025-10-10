#include <iostream>
#include <vector>

int kadanesAlgorithm(const std::vector<int>& arr) {
    int maxEndingHere = arr[0];
    int maxSoFar = arr[0];

    for (int i = 1; i < arr.size(); ++i) {
        maxEndingHere = std::max(arr[i], maxEndingHere + arr[i]);
        maxSoFar = std::max(maxSoFar, maxEndingHere);
    }

    return maxSoFar;
}

int main() {
    std::vector<int> arr = {1, -3, 2, 1, -1};

    int maxSubarraySum = kadanesAlgorithm(arr);

    std::cout << "Maximum subarray sum: " << maxSubarraySum << std::endl;

    return 0;
}
