#include<iostream>
using namespace std;

int main(){

int num, result=0;
cout<<"Enter a positive integer: ";
cin>>num;


int temporary = num;
while(num>0){
    int dig = num%10;
    result = result*10+dig;
    num=num/10;
}

num = temporary;


   if(result==num){
    cout<<"Number is a palindrome.";
}
   else
    cout<<"Not a palindrome.";

return 0;
}
