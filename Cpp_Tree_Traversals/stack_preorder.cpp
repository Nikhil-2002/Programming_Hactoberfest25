
#include <bits/stdc++.h>
using namespace std;

struct Node {
    int data;
    Node* left;
    Node* right;
    Node(int val): data(val), left(nullptr), right(nullptr) {}
};

vector<int> iterative_preorder(Node* root) {
    vector<int> res;
    if (!root) return res;
    stack<Node*> st;
    st.push(root);
    while (!st.empty()) {
        Node* node = st.top(); st.pop();
        res.push_back(node->data);
        if (node->right) st.push(node->right);
        if (node->left) st.push(node->left);
    }
    return res;
}

int main() {
    Node* root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);

    auto ans = iterative_preorder(root);
    cout << "Iterative Preorder: ";
    for (int v : ans) cout << v << " ";
    cout << endl;
    return 0;
}
