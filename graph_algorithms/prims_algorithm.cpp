#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int primMST(int V, vector<vector<pair<int, int>>>& adj) {
    vector<int> key(V, 1e9);
    vector<int> inMST(V, 0);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    key[0] = 0;
    pq.push({0, 0}); // {weight, vertex}
    int totalWeight = 0;

    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        if (inMST[u]) continue;
        inMST[u] = 1;
        totalWeight += key[u];

        for (auto& edge : adj[u]) {
            int v = edge.first;
            int w = edge.second;
            if (!inMST[v] && w < key[v]) {
                key[v] = w;
                pq.push({key[v], v});
            }
        }
    }

    return totalWeight;
}

int main() {
    int V = 5;
    vector<vector<pair<int, int>>> adj(V);

    adj[0].push_back({1, 2});
    adj[0].push_back({3, 6});
    adj[1].push_back({0, 2});
    adj[1].push_back({2, 3});
    adj[1].push_back({3, 8});
    adj[1].push_back({4, 5});
    adj[2].push_back({1, 3});
    adj[2].push_back({4, 7});
    adj[3].push_back({0, 6});
    adj[3].push_back({1, 8});
    adj[4].push_back({1, 5});
    adj[4].push_back({2, 7});

    cout << "Total weight of MST: " << primMST(V, adj) << endl;
    return 0;
}
