#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

// Function to check whether the string is palindrome
string isPalindrome(string S)
{
	// Stores the reverse of the string S
	string P = S;

	// Reverse the string P
	reverse(P.begin(), P.end());

	if (S == P) {
		return "Yes";
	}

	else {
		return "No";
	}
}

//Function to check if string is Palindrome without using extra space
string isPalind(string &s){
    	int l=0, r = s.size()-1;
	while(l<r){
		if (s[l++] != s[r--]) return "false";
	}
	return "true";

}

// Driver Code
int main()
{
    string S;
    cout<<"Enter the string: ";
    cin>>S;
    cout << isPalindrome(S);
    cout<<isPalind(S);
	
    return 0;
}
