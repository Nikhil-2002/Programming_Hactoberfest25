import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Depth-First Search (DFS) algorithm for traversing a graph.
 * DFS explores as far as possible along each branch before backtracking.
 * This implementation uses recursion, which implicitly uses a stack.
 */
public class DFS {

    private int V; // Number of vertices
    private List<List<Integer>> adj; // Adjacency list

    /**
     * Constructor to initialize the graph.
     * @param v The number of vertices.
     */
    public DFS(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
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
        adj.get(w).add(v);
    }

    /**
     * A recursive helper function for DFS traversal.
     * @param v The current vertex.
     * @param visited A boolean array to keep track of visited vertices.
     */
    private void DFSHelper(int v, boolean[] visited) {
        // Mark the current node as visited and print it.
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex.
        for (int n : adj.get(v)) {
            if (!visited[n]) {
                DFSHelper(n, visited);
            }
        }
    }

    /**
     * Performs DFS traversal starting from a given source vertex.
     * @param s The source vertex to start the traversal from.
     */
    public void traverse(int s) {
        boolean[] visited = new boolean[V];
        System.out.println("Depth-First Traversal starting from vertex " + s + ":");
        DFSHelper(s, visited);
        System.out.println();
    }

    /**
     * Main method to test the DFS implementation.
     */
    public static void main(String[] args) {
        // Create a graph with 6 vertices.
        DFS g = new DFS(6);

        // Add edges to the graph.
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);

        // Perform DFS traversal starting from vertex 0.
        g.traverse(0);
    }
}