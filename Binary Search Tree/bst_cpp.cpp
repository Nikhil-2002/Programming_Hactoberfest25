#include <iostream>
using namespace std;

struct Node {
    int key;
    Node* left;
    Node* right;

    Node(int item) {
        key = item;
        left = right = nullptr;
    }
};

class BST {
    Node* root;

public:
    BST() {
        root = nullptr;
    }

    // Insert function
    void insert(int key) {
        root = insertRec(root, key);
    }

    Node* insertRec(Node* root, int key) {
        if (root == nullptr)
            return new Node(key);

        if (key < root->key)
            root->left = insertRec(root->left, key);
        else if (key > root->key)
            root->right = insertRec(root->right, key);

        return root;
    }

    // Inorder Traversal
    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node* root) {
        if (root != nullptr) {
            inorderRec(root->left);
            cout << root->key << " ";
            inorderRec(root->right);
        }
    }

    // Preorder Traversal
    void preorder() {
        preorderRec(root);
    }

    void preorderRec(Node* root) {
        if (root != nullptr) {
            cout << root->key << " ";
            preorderRec(root->left);
            preorderRec(root->right);
        }
    }

    // Postorder Traversal
    void postorder() {
        postorderRec(root);
    }

    void postorderRec(Node* root) {
        if (root != nullptr) {
            postorderRec(root->left);
            postorderRec(root->right);
            cout << root->key << " ";
        }
    }
};

int main() {
    BST tree;

    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);

    cout << "Inorder: ";
    tree.inorder();

    cout << "\nPreorder: ";
    tree.preorder();

    cout << "\nPostorder: ";
    tree.postorder();

    return 0;
}
