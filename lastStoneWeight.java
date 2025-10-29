class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq= new  PriorityQueue<>(Collections.reverseOrder());

        for(int val:stones) pq.add(val);

        while(pq.size()>1){
            int y = pq.remove();
            int x = pq.remove();

            if(x!=y) pq.add(y-x);
        }
        return pq.size()>0? pq.remove() : 0;
    }
}
