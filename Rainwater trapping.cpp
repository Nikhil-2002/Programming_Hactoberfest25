/*
Problem: Trapping Rain Water
We need to calculate how much water can be trapped between bars of different heights after raining.
Approach (Prefix–Suffix / Auxiliary Arrays Method)

The main idea is: For each element, the amount of water that can be trapped above it depends on The maximum height to its left and the maximum height to its right

Water trapped at that position = min(maxLeft, maxRight) - height[i]

So, we first precompute two arrays:
left[i] → highest bar from the start to index i
right[i] → highest bar from the end to index i
Then, for every index i, we find: water[i] = min(left[i], right[i]) - height[i]
Add up all these values to get the total trapped water.
*/
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        if (n == 0) return 0;

        // Step 1: Precompute the maximum height to the left of each bar
        vector<int> leftMax(n);
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = max(leftMax[i - 1], height[i]);
        }

        // Step 2: Precompute the maximum height to the right of each bar
        vector<int> rightMax(n);
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = max(rightMax[i + 1], height[i]);
        }

        // Step 3: Calculate trapped water for each index
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = min(leftMax[i], rightMax[i]);
            totalWater += (waterLevel - height[i]);
        }

        return totalWater;
    }
};

int main() {
    Solution s;
    vector<int> height = {4,2,0,3,2,5};
    cout << "Total trapped water: " << s.trap(height) << endl;
    return 0;
}

/*The time complexity of this approach is O(n) since we make three separate passes through the array — one to compute the leftMax array, one to compute the rightMax array, and one final pass to calculate the total trapped water. Each step takes linear time, so the overall complexity remains O(n).

The space complexity, however, is O(n) because we use two additional arrays, leftMax and rightMax, to store the maximum heights on each side for every index. While this approach is easy to understand and straightforward to implement, it does require extra memory. The main advantage is its clarity and simplicity, but the downside is the use of additional space — which can be optimized using a more efficient two-pointer approach that reduces the space complexity to O(1).*/