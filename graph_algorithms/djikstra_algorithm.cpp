#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void dijkstra(int V, vector<vector<pair<int, int>>>& adj, int source) {
    vector<int> dist(V, 1e9);
    dist[source] = 0;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, source}); // {distance, node}

    while (!pq.empty()) {
        int u = pq.top().second;
        int d = pq.top().first;
        pq.pop();

        if (d > dist[u]) continue; 

        for (auto& edge : adj[u]) {
            int v = edge.first;
            int w = edge.second;

            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pq.push({dist[v], v});
            }
        }
    }

    cout << "Shortest distances from source " << source << ": ";
    for (int i = 0; i < V; i++) cout << dist[i] << " ";
    cout << endl;
}

int main() {
    int V = 5;
    vector<vector<pair<int, int>>> adj(V);

    adj[0].push_back({1, 2});
    adj[0].push_back({2, 4});
    adj[1].push_back({0, 2});
    adj[1].push_back({2, 1});
    adj[1].push_back({3, 7});
    adj[2].push_back({0, 4});
    adj[2].push_back({1, 1});
    adj[2].push_back({4, 3});
    adj[3].push_back({1, 7});
    adj[3].push_back({4, 2});
    adj[4].push_back({2, 3});
    adj[4].push_back({3, 2});

    dijkstra(V, adj, 0);
    return 0;
}
