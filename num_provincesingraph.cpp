#include<bits/stdc++.h>
using namespace std;
//implementation of the graph data structure wit the requireed functions
class Graph{
    unordered_map<int,list<int>> map;
    int num_nodes;
    public:
    // s number of nodes
    Graph(int s){
       this->num_nodes=s;
    }
    //functions for adding edges between the nodes
    void addedge(int u,int v,bool  dir){
        map[u].push_back(v);
        if(dir==0){
            map[v].push_back(u);
        }
    }
    //function for printing the  adjacency list
    void print_graph(){
        for(auto i :map){
            cout<<i.first<<"->";
            for(auto j: i.second){
                cout<<j<<",";
            }
            cout<<endl;
        }
    }
    private:
    //the dfs function
    void dfs(int node,int* visited,vector<int> &result){
        visited[node]=1;
        result.push_back(node);
        for(auto i :map[node]){
            if(!visited[i]){
             dfs(i,visited,result);
            }
        }
    }
    public:
    //The function to find number of provinces in the graph
    int num_province(){
        int visited[num_nodes]={0};
        vector<int>res;
        int province_counter=0;
        for(int i =1;i<=num_nodes;i++){
            if(!visited[i]){
                province_counter++;
                dfs(i,visited,res);
            }
        }
        return province_counter;
    }
};

int main(){
    int n,m;
    //taking number of the nodes as n and number of edges as m
    cout<<"enter the number of nodes and edges : ";
    cin>>n>>m;
    cout<<"Enter the edges : "<<endl;
   Graph g1(n);
   //the edge is added between u and v
   for(int i=0;i<m;i++){
        int u,v;
        cin>>u>>v;
        g1.addedge(u,v,0);
   }
   cout<<"The Adjacency list :"<<endl;
   g1.print_graph();
   //calling the number of province funtction
   int provinces=g1.num_province();
   cout<<"The number of provinces in the graph is : "<<provinces<<endl;
return 0;
}

