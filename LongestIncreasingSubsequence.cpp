#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

// Returns one of the longest increasing subsequences using an O(n log n) approach.
std::vector<int> longestIncreasingSubsequence(const std::vector<int>& nums) {
    if (nums.empty()) {
        return {};
    }

    std::vector<int> tails;          // Smallest possible tail for subsequence of length i + 1
    std::vector<int> tailIndices;    // Index in nums that produced the tail value
    std::vector<int> parent(nums.size(), -1); // Predecessor index for reconstruction

    tails.reserve(nums.size());
    tailIndices.reserve(nums.size());

    int lisLastIndex = 0;

    for (std::size_t i = 0; i < nums.size(); ++i) {
        const int value = nums[i];
        auto it = std::lower_bound(tails.begin(), tails.end(), value);
        std::size_t idx = static_cast<std::size_t>(std::distance(tails.begin(), it));

        if (it == tails.end()) {
            tails.push_back(value);
            tailIndices.push_back(static_cast<int>(i));
        } else {
            *it = value;
            tailIndices[idx] = static_cast<int>(i);
        }

        if (idx > 0) {
            parent[i] = tailIndices[idx - 1];
        }

        if (idx == tails.size() - 1) {
            lisLastIndex = tailIndices[idx];
        }
    }

    std::vector<int> sequence;
    for (int idx = lisLastIndex; idx != -1; idx = parent[idx]) {
        sequence.push_back(nums[idx]);
    }
    std::reverse(sequence.begin(), sequence.end());
    return sequence;
}

std::vector<int> readInputSequence() {
    std::cout << "Enter integers separated by spaces (press Enter to use the default example):" << std::endl;
    std::string line;
    std::getline(std::cin, line);

    if (line.empty()) {
        return {10, 9, 2, 5, 3, 7, 101, 18};
    }

    std::istringstream stream(line);
    std::vector<int> numbers;
    int value;
    while (stream >> value) {
        numbers.push_back(value);
    }

    return numbers;
}

int main() {
    std::vector<int> numbers = readInputSequence();

    if (numbers.empty()) {
        std::cout << "No numbers provided. Nothing to compute." << std::endl;
        return 0;
    }

    std::vector<int> lis = longestIncreasingSubsequence(numbers);

    std::cout << "Input sequence: ";
    for (std::size_t i = 0; i < numbers.size(); ++i) {
        std::cout << numbers[i] << (i + 1 < numbers.size() ? ' ' : '\n');
    }

    std::cout << "Longest Increasing Subsequence (length " << lis.size() << "): ";
    for (std::size_t i = 0; i < lis.size(); ++i) {
        std::cout << lis[i] << (i + 1 < lis.size() ? ' ' : '\n');
    }

    return 0;
}
