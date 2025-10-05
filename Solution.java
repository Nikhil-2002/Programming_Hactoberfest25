// Number of Sub-array with odd sum
class Solution {
    public int numOfSubarrays(int[] arr) {
        int odd = 0;
        int even = 1;
        int sum = 0;
        int result = 0;
        int mod = 1000000007;

        for(int num : arr)
        {
            sum += num;
            if(sum % 2 == 0)
            {
                result = (result +odd) % mod;
                even++;
            }
            else
            {
                result = (result +even) % mod;;
                odd++;
            }

        }
        return result;
    }
}
