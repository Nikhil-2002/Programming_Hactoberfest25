import java.util.Arrays;

/**
 * Implements the Bellman-Ford algorithm to find the shortest paths from a single
 * source vertex to all other vertices in a weighted directed graph.
 * This algorithm can handle graphs with negative edge weights and can detect
 * negative cycles.
 */
public class BellmanFord {

    // Inner class to represent a weighted edge in the graph
    static class Edge {
        int src, dest, weight;
        Edge() {
            src = dest = weight = 0;
        }
    }

    int V, E; // Number of vertices and edges
    Edge[] edge;

    /**
     * Constructor to create a graph with V vertices and E edges.
     * @param v Number of vertices.
     * @param e Number of edges.
     */
    public BellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i) {
            edge[i] = new Edge();
        }
    }

    /**
     * Implements the Bellman-Ford algorithm.
     * @param src The source vertex.
     */
    public void findShortestPath(int src) {
        int[] dist = new int[V];

        // Step 1: Initialize distances from src to all other vertices as INFINITE.
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Step 2: Relax all edges |V| - 1 times. A simple shortest
        // path from src to any other vertex can have at-most |V| - 1 edges.
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = edge[j].src;
                int v = edge[j].dest;
                int weight = edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // Step 3: Check for negative-weight cycles. The above step
        // guarantees shortest distances if the graph doesn't contain a negative-weight cycle.
        // If we get a shorter path, then there is a cycle.
        for (int j = 0; j < E; ++j) {
            int u = edge[j].src;
            int v = edge[j].dest;
            int weight = edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains a negative weight cycle!");
                return;
            }
        }

        printSolution(src, dist);
    }

    private void printSolution(int src, int[] dist) {
        System.out.println("Bellman-Ford Algorithm: Shortest distances from source vertex " + src);
        for (int i = 0; i < V; ++i) {
            System.out.println("Vertex " + i + " -> Distance: " + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }

    /**
     * Main method to test the Bellman-Ford implementation.
     */
    public static void main(String[] args) {
        int V = 5;
        int E = 8;
        BellmanFord graph = new BellmanFord(V, E);

        // Add edges
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        graph.findShortestPath(0);
    }
}