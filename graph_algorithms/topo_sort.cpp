#include <iostream>
#include <vector>
#include <stack>
using namespace std;

void topoDFS(int node, const vector<vector<int>>& adj, vector<int>& visited, stack<int>& st) {
    visited[node] = 1;
    for (int neigh : adj[node]) {
        if (!visited[neigh]) {
            topoDFS(neigh, adj, visited, st);
        }
    }
    st.push(node); // push after visiting all neighbors
}

void topoSortDFS(int V, const vector<vector<int>>& adj) {
    vector<int> visited(V, 0);
    stack<int> st;

    for (int i = 0; i < V; i++) {
        if (!visited[i])
            topoDFS(i, adj, visited, st);
    }

    cout << "Topological Order (DFS-based): ";
    while (!st.empty()) {
        cout << st.top() << " ";
        st.pop();
    }
    cout << endl;
}

int main() {
    int V = 6;
    vector<vector<int>> adj(V);

    adj[5] = {2, 0};
    adj[4] = {0, 1};
    adj[2] = {3};
    adj[3] = {1};

    topoSortDFS(V, adj);
    return 0;
}
