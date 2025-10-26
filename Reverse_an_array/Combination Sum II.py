from typing import List

class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        candidates.sort()
        res = []

        def dfs(target, start, comb):
            if target < 0:
                return
            if target == 0:
                res.append(comb)
                return
            for i in range(start, len(candidates)):
                if i > start and candidates[i] == candidates[i - 1]:
                    continue
                if candidates[i] > target:
                    break
                dfs(target - candidates[i], i + 1, comb + [candidates[i]])

        dfs(target, 0, [])
        return res


if __name__ == "__main__":
    # Example input
    candidates = [10, 1, 2, 7, 6, 1, 5]
    target = 8

    # Create object
    sol = Solution()

    # Call function
    result = sol.combinationSum2(candidates, target)

    # Print output
    print("Unique combinations that sum to target:")
    for comb in result:
        print(comb)
