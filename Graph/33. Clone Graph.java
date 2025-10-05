class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return node; // node is empty
        Queue<Node> q = new LinkedList<>(); 
        // to store old node to access neighbors
        HashMap<Integer, Node> oldToNew = new HashMap<>(); 
        //  to map old node to new node
        
        // load first node
        oldToNew.put(node.val, new Node(node.val)); 
        q.add(node);

        while (!q.isEmpty()) { // until node in q
            Node curr = q.poll(); // set curr node
            Node clone = oldToNew.get(curr.val); // get curr cloned node
            for (Node neighbor : curr.neighbors) { // iterate over curr node neighbors to add those to curr cloned node
                if ( !oldToNew.containsKey(neighbor.val)) {  // check if neighbor already cloned
                    oldToNew.put(neighbor.val, new Node(neighbor.val)); // if not create one
                    q.add(neighbor); // add that to queue 
                }
                clone.neighbors.add(oldToNew.get(neighbor.val)); // now access cloned neighbor of curr node (old) and add that to curr cloned node (new)
            }
        } //   # this while loop automatically clone all node and store that in oldToNew {}
        return oldToNew.get(node.val); // returns new cloned node (we didn't modify node so it points first node)
        
    }
}