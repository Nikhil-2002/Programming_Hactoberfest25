#include <iostream>
using namespace std;

// Define a node for the linked list
struct Node {
    int data;
    Node* next;

    Node(int val) {
        data = val;
        next = nullptr;
    }
};

// Function to insert a new node at the end of the list
void insertAtEnd(Node*& head, int val) {
    Node* newNode = new Node(val);
    if (head == nullptr) {  // if list is empty
        head = newNode;
        return;
    }
    Node* temp = head;
    while (temp->next != nullptr) {
        temp = temp->next;
    }
    temp->next = newNode;
}

// Function to print the linked list
void printList(Node* head) {
    while (head != nullptr) {
        cout << head->data << " ";
        head = head->next;
    }
    cout << endl;
}

// Function to merge two sorted linked lists
Node* mergeTwoSortedLists(Node* l1, Node* l2) {
    // If any list is empty, return the other
    if (!l1) return l2;
    if (!l2) return l1;

    // Create a dummy node to simplify handling of the head
    Node* dummy = new Node(-1);
    Node* tail = dummy;

    // Traverse both lists and pick smaller elements
    while (l1 && l2) {
        if (l1->data <= l2->data) {
            tail->next = l1;
            l1 = l1->next;
        } else {
            tail->next = l2;
            l2 = l2->next;
        }
        tail = tail->next;
    }

    // If any nodes remain in one list, connect them
    if (l1) tail->next = l1;
    if (l2) tail->next = l2;

    // Return merged list (skip dummy)
    return dummy->next;
}

int main() {
    Node* list1 = nullptr;
    Node* list2 = nullptr;

    int n1, n2, val;

    cout << "Enter number of elements in first sorted list: ";
    cin >> n1;
    cout << "Enter elements of first sorted list: ";
    for (int i = 0; i < n1; i++) {
        cin >> val;
        insertAtEnd(list1, val);
    }

    cout << "Enter number of elements in second sorted list: ";
    cin >> n2;
    cout << "Enter elements of second sorted list: ";
    for (int i = 0; i < n2; i++) {
        cin >> val;
        insertAtEnd(list2, val);
    }

    cout << "\nMerged Sorted List: ";
    Node* mergedList = mergeTwoSortedLists(list1, list2);
    printList(mergedList);

    return 0;
}
