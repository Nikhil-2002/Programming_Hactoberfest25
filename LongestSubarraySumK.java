import java.util.*;
public class LongestSubarraySumK 
{
    public static int longestSubarrayWithSumK(int[] arr, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int sum = 0, maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // If sum equals K, whole array till i has sum K
            if (sum == k) {
                maxLen = i + 1;
            }
            if (prefixSumMap.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - prefixSumMap.get(sum - k));
            }
            // Store sum if it's not already present
            prefixSumMap.putIfAbsent(sum, i);
        }
        return maxLen;
    }
    public static void main(String[] args)
     {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter array size:");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println("Enter K:");
        int k = sc.nextInt();
        int result = longestSubarrayWithSumK(a, k);
        System.out.println("Length of longest subarray with sum " + k + " is: " + result);
    }
}

