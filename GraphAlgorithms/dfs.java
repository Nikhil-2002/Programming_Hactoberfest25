// File: GraphAlgorithms/DFS.java
import java.util.*;

public class dfs {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public dfs(int v) {
        vertices = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src); // Remove if directed graph
    }

    void Dfs(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS Traversal starting from node " + start + ": ");
        dfsUtil(start, visited);
        System.out.println();
    }

    private void dfsUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        dfs graph = new dfs(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.Dfs(0);
    }
}

