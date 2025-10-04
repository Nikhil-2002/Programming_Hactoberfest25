/**
 * Implements the Floyd-Warshall algorithm to find the shortest paths between
 * all pairs of vertices in a weighted graph. It is a dynamic programming
 * algorithm that can handle positive or negative edge weights but not
 * negative cycles.
 */
public class FloydWarshall {

    final static int INF = 99999; // A value to represent infinity

    /**
     * Solves the all-pairs shortest path problem using Floyd-Warshall algorithm.
     * @param graph The adjacency matrix representation of the graph.
     */
    public void findShortestPaths(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];

        // Initialize the solution matrix same as input graph matrix.
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        /*
         * Add all vertices one by one to the set of intermediate vertices.
         * ---> Before start of an iteration, we have shortest distances between all
         * pairs of vertices such that the shortest distances consider only the
         * vertices in set {0, 1, 2, .. k-1} as intermediate vertices.
         * ---> After the end of an iteration, vertex no. k is added to the set of
         * intermediate vertices and the set becomes {0, 1, 2, .. k}.
         */
        for (int k = 0; k < V; k++) {
            // Pick all vertices as source one by one
            for (int i = 0; i < V; i++) {
                // Pick all vertices as destination for the above picked source
                for (int j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from i to j,
                    // then update the value of dist[i][j]
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(dist);
    }

    private void printSolution(int[][] dist) {
        int V = dist.length;
        System.out.println("Floyd-Warshall Algorithm: Matrix of shortest distances between every pair of vertices");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(dist[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * Main method to test the Floyd-Warshall implementation.
     */
    public static void main(String[] args) {
        int V = 4;
        int[][] graph = {
            {0, 5, INF, 10},
            {INF, 0, 3, INF},
            {INF, INF, 0, 1},
            {INF, INF, INF, 0}
        };

        FloydWarshall fw = new FloydWarshall();
        fw.findShortestPaths(graph);
    }
}