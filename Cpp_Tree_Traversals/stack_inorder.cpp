

#include <bits/stdc++.h>
using namespace std;

struct Node {
    int data;
    Node* left;
    Node* right;
    Node(int val): data(val), left(nullptr), right(nullptr) {}
};

vector<int> iterative_inorder(Node* root) {
    vector<int> res;
    stack<Node*> st;
    Node* curr = root;
    while (curr || !st.empty()) {
        while (curr) {
            st.push(curr);
            curr = curr->left;
        }
        curr = st.top(); st.pop();
        res.push_back(curr->data);
        curr = curr->right;
    }
    return res;
}

int main() {
    Node* root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);

    auto ans = iterative_inorder(root);
    cout << "Iterative Inorder: ";
    for (int v : ans) cout << v << " ";
    cout << endl;
    return 0;
}
