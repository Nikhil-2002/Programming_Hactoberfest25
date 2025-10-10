#include <iostream>
#include <vector>
#include <climits>

const int V = 6; // Number of vertices in the graph

// Function to find the vertex with the minimum distance value from the set of vertices not yet included in the shortest path tree
int minDistance(const std::vector<int>& dist, const std::vector<bool>& sptSet) {
    int minDist = INT_MAX, minIndex = -1;
    for (int v = 0; v < V; ++v) {
        if (!sptSet[v] && dist[v] < minDist) {
            minDist = dist[v];
            minIndex = v;
        }
    }
    return minIndex;
}

// Function to print the constructed distance array
void printSolution(const std::vector<int>& dist) {
    std::cout << "Vertex \t Distance from Source" << std::endl;
    for (int i = 0; i < V; ++i) {
        std::cout << i << " \t " << dist[i] << std::endl;
    }
}

// Dijkstra's algorithm to find the shortest path from source to all vertices
void dijkstra(const std::vector<std::vector<int>>& graph, int src) {
    std::vector<int> dist(V, INT_MAX); // The output array to store the shortest distance from the source vertex
    std::vector<bool> sptSet(V, false); // Set to keep track of vertices included in the shortest path tree

    dist[src] = 0; // Distance from the source to itself is 0

    for (int count = 0; count < V - 1; ++count) {
        int u = minDistance(dist, sptSet);

        sptSet[u] = true;

        for (int v = 0; v < V; ++v) {
            if (!sptSet[v] && graph[u][v] && dist[u] != INT_MAX && dist[u] + graph[u][v] < dist[v]) {
                dist[v] = dist[u] + graph[u][v];
            }
        }
    }

    printSolution(dist);
}

int main() {
    std::vector<std::vector<int>> graph = {
        {0, 2, 4, 0, 0, 0},
        {2, 0, 3, 2, 0, 0},
        {4, 3, 0, 0, 1, 0},
        {0, 2, 0, 0, 3, 2},
        {0, 0, 1, 3, 0, 2},
        {0, 0, 0, 2, 2, 0}
    };

    int src = 0; // Source vertex

    dijkstra(graph, src);

    return 0;
}
