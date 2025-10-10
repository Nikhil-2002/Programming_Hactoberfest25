#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

void rotate(vector<int>& nums, int k) {
    k = k % nums.size();
    reverse(nums.begin(), nums.end() - k);
    reverse(nums.end() - k, nums.end());
    reverse(nums.begin(), nums.end());
}

int main() {
    vector<int> nums;
    int n, k;

    cout << "Enter the number of elements in the array: ";
    cin >> n;

    cout << "Enter " << n << " elements: ";
    for (int i = 0; i < n; ++i) {
        int num;
        cin >> num;
        nums.push_back(num);
    }

    cout << "Enter the number of positions to rotate to the right: ";
    cin >> k;

    cout << "Original Array: ";
    for (const auto& num : nums) {
        cout << num << " ";
    }
    cout << endl;

    rotate(nums, k);

    cout << "Array after rotating by " << k << " positions to the right: ";
    for (const auto& num : nums) {
        cout << num << " ";
    }
    cout << endl;

    return 0;
}
