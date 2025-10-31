

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int data;
    Node* left;
    Node* right;
    Node(int val): data(val), left(nullptr), right(nullptr) {}
};

vector<int> level_order(Node* root) {
    vector<int> res;
    if (!root) return res;
    queue<Node*> q;
    q.push(root);
    while (!q.empty()) {
        Node* node = q.front(); q.pop();
        res.push_back(node->data);
        if (node->left) q.push(node->left);
        if (node->right) q.push(node->right);
    }
    return res;
}

int main() {
    Node* root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);
    root->right->left = new Node(6);

    auto ans = level_order(root);
    cout << "Level Order: ";
    for (int v : ans) cout << v << " ";
    cout << endl;
    return 0;
}
