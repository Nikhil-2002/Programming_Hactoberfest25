import java.util.*;

/**
 * Implements Dijkstra's algorithm to find the shortest path from a single source
 * to all other vertices in a weighted graph with non-negative edge weights.
 * It uses a priority queue to efficiently select the vertex with the smallest distance.
 */
public class Dijkstra {

    // Inner class to represent a node in the priority queue
    static class Node implements Comparator<Node> {
        public int vertex;
        public int cost;

        public Node() {}

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }

    private int V; // Number of vertices
    private List<List<Node>> adj; // Adjacency list

    public Dijkstra(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    /**
     * Adds a weighted edge to the graph.
     * @param u Source vertex.
     * @param v Destination vertex.
     * @param weight The weight of the edge.
     */
    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Node(v, weight));
        adj.get(v).add(new Node(u, weight)); // For undirected graph
    }

    /**
     * Implements Dijkstra's algorithm.
     * @param src The source vertex.
     */
    public void findShortestPath(int src) {
        // Priority queue to store vertices that are being pre-processed.
        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());

        // Array to store the shortest distance from src to i.
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Add source node to the priority queue and initialize its distance as 0.
        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()) {
            // Extract the vertex with the minimum distance value.
            int u = pq.poll().vertex;

            // Visit all adjacent vertices of u.
            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.cost;

                // If there is a shorter path to v through u.
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        // Print the shortest distances.
        printSolution(src, dist);
    }

    private void printSolution(int src, int[] dist) {
        System.out.println("Dijkstra's Algorithm: Shortest distances from source vertex " + src);
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + " -> Distance: " + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }

    /**
     * Main method to test Dijkstra's algorithm.
     */
    public static void main(String[] args) {
        int V = 5;
        Dijkstra g = new Dijkstra(V);

        g.addEdge(0, 1, 9);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(0, 4, 3);
        g.addEdge(2, 1, 2);
        g.addEdge(2, 3, 4);

        g.findShortestPath(0);
    }
}