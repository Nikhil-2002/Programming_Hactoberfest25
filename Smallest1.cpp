class Solution {
public:
    vector<int> smallestRange(vector<vector<int>>& nums) {
        // Min-Heap: stores (value, list index, element index)
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> minHeap;
        int curMax = numeric_limits<int>::min();

        // Initialize the heap with the first element of each list
        for (int i = 0; i < nums.size(); i++) {
            minHeap.push({nums[i][0], i, 0});
            curMax = max(curMax, nums[i][0]);
        }
        // Track the smallest range
        vector<int> smallRange = {0, numeric_limits<int>::max()};

        while (!minHeap.empty()) {
            // Get the minimum element from the heap
            vector<int> curr = minHeap.top();
            minHeap.pop();
            int curMin = curr[0], listIdx = curr[1], elemIdx = curr[2];

            // Update the smallest range if a better one is found
            if ((curMax - curMin < smallRange[1] - smallRange[0]) ||
                (curMax - curMin == smallRange[1] - smallRange[0] && curMin < smallRange[0])) {
                smallRange[0] = curMin;
                smallRange[1] = curMax;
            }

            // Move to the next element in the same list
            if (elemIdx + 1 < nums[listIdx].size()) {
                int nextVal = nums[listIdx][elemIdx + 1];
                minHeap.push({nextVal, listIdx, elemIdx + 1});
                curMax = max(curMax, nextVal);
            } else {
                // If any list is exhausted, stop
                break;
            }
        }
        return smallRange;
    }
};
