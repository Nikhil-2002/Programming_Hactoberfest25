import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implements the Breadth-First Search (BFS) algorithm for traversing a graph.
 * BFS explores neighbor nodes first before moving to the next level of neighbors.
 * It uses a queue to keep track of the next nodes to visit.
 */
public class BFS {

    private int V; // Number of vertices
    private List<List<Integer>> adj; // Adjacency list representation

    /**
     * Constructor to initialize the graph with a given number of vertices.
     * @param v The number of vertices.
     */
    public BFS(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; ++i) {
            adj.add(new ArrayList<>());
        }
    }

    /**
     * Adds an edge to the undirected graph.
     * @param v The source vertex.
     * @param w The destination vertex.
     */
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v); // For an undirected graph
    }

    /**
     * Performs BFS traversal starting from a given source vertex.
     * @param s The source vertex to start the traversal from.
     */
    public void traverse(int s) {
        // A boolean array to keep track of visited vertices.
        boolean[] visited = new boolean[V];
        
        // A queue for the BFS traversal.
        Queue<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it.
        visited[s] = true;
        queue.add(s);

        System.out.println("Breadth-First Traversal starting from vertex " + s + ":");

        while (!queue.isEmpty()) {
            // Dequeue a vertex from the queue and print it.
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s.
            // If an adjacent has not been visited, mark it visited and enqueue it.
            for (int n : adj.get(s)) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    /**
     * Main method to test the BFS implementation.
     */
    public static void main(String[] args) {
        // Create a graph with 6 vertices.
        BFS g = new BFS(6);

        // Add edges to the graph.
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);

        // Perform BFS traversal starting from vertex 0.
        g.traverse(0);
    }
}