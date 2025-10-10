//Author: Divya Raj (aka guru_divine)
#include<bits/stdc++.h>
using namespace std;

// TC -> O(n)
// SC -> O(1)

int main() {
    cout << "Enter the number of elements to be considered: ";
    int n; cin >> n;
    int cntEven=0, cntOdd=0;
    cout << "Enter " << n << " elements: " << endl;

    for(int i=0; i<n; ++i) {
        int x; cin >> x;
        if(x&1) cntOdd++;
        else cntEven++;
    }
    cout << "Number of even elements in the list: " << cntEven << endl;
    cout << "Number of odd elements in the list: " << cntOdd << endl;
}
