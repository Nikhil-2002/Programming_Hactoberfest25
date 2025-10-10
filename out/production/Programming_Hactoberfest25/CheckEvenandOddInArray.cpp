#include<iostream>
using namespace std;

int main(){

int size;
cout<<"Enter Size of Array: ";
cin>>size;

int arr[size];

cout<<" Enter Elements in the array: ";

for(int i=0; i<size; i++){
cin>>arr[i];
}

cout<<" Even elements: ";

for(int i=0; i<size; i++){
if(arr[i]%2==0){
cout<<arr[i]<<" ";
}
}

cout<<endl;
cout<<" Odd elements: ";

for(int i=0; i<size; i++){
if(arr[i]%2==1){
cout<<arr[i]<<" ";
}
}

}
