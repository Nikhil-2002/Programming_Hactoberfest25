class Solution {
    public int[] sortArray(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val:nums){
            pq.add(val);
        }
        int i=0;
        while(pq.size()>0){
            nums[i++] = pq.poll();
        }
        return nums;
    }
}
