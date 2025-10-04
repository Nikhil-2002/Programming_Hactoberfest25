#include <iostream>
#include <vector>
using namespace std;

void dfs(int node, const vector<vector<int>>& adj, vector<int>& visited) {
    visited[node] = 1;
    cout << node << " ";

    for (int neigh : adj[node]) {
        if (!visited[neigh]) {
            dfs(neigh, adj, visited);
        }
    }
}

int main() {
    int V = 6;
    vector<vector<int>> adj(V);

    adj[0] = {1, 2};
    adj[1] = {0, 3, 4};
    adj[2] = {0, 4};
    adj[3] = {1, 5};
    adj[4] = {1, 2, 5};
    adj[5] = {3, 4};

    vector<int> visited(V, 0);
    cout << "DFS Traversal: ";
    dfs(0, adj, visited);
    cout << endl;

    return 0;
}
