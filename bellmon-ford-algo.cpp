#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define V 5
#define E 8

void bellmanFord(int graph[][3]) {
    int dist[V];
    for (int i = 0; i < V; i++) {
        dist[i] = INT_MAX;
    }
    dist[0] = 0;

    for (int i = 0; i < V - 1; i++) {
        for (int j = 0; j < E; j++) {
            int u = graph[j][0];
            int v = graph[j][1];
            int weight = graph[j][2];
            if (dist[u] != INT_MAX && dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
            }
        }
    }

    for (int i = 0; i < V; i++) {
        printf("Distance from source to vertex %d: %d\n", i, dist[i]);
    }
}

int main() {
    int graph[E][3] = {
        {0, 1, -1},
        {0, 2, 4},
        {1, 2, 3},
        {1, 3, 2},
        {1, 4, 2},
        {3, 2, 5},
        {3, 1, 1},
        {4, 3, -3}
    };

    bellmanFord(graph);

    return 0;
}