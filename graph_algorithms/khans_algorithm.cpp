#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void topoSortKahn(int V, const vector<vector<int>>& adj) {
    vector<int> indegree(V, 0);

    for (int i = 0; i < V; i++) {
        for (int neigh : adj[i])
            indegree[neigh]++;
    }

    queue<int> q;
    for (int i = 0; i < V; i++) {
        if (indegree[i] == 0)
            q.push(i);
    }

    cout << "Topological Order (Kahn's Algorithm): ";
    int count = 0;

    while (!q.empty()) {
        int node = q.front();
        q.pop();
        cout << node << " ";
        count++;

        for (int neigh : adj[node]) {
            indegree[neigh]--;
            if (indegree[neigh] == 0)
                q.push(neigh);
        }
    }

    if (count != V)
        cout << "\nGraph contains a cycle (not a DAG)";
    cout << endl;
}

int main() {
    int V = 6;
    vector<vector<int>> adj(V);

    adj[5] = {2, 0};
    adj[4] = {0, 1};
    adj[2] = {3};
    adj[3] = {1};

    topoSortKahn(V, adj);
    return 0;
}
