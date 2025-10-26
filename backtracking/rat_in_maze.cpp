

/*
Time Complexity: O(2^(n^2)) in worst case
Space Complexity: O(n^2) for recursion stack and solution matrix

Explanation:
The Rat in Maze problem is a classic backtracking problem where we need to find
a path from the top-left corner to the bottom-right corner of a maze.

Algorithm:
1. Start from the top-left corner (0,0)
2. Check if the current position is valid (within bounds and not blocked)
3. Mark the current position as part of the solution path
4. Try moving right first, then down
5. If we reach the destination, we found a solution
6. If no path is found from current position, backtrack by unmarking it
7. Continue until all possible paths are explored

Key Points:
- The rat can only move right or down (in some variations, all 4 directions are allowed)
- 1 represents an open path, 0 represents a blocked path
- We use backtracking to explore all possible paths
- The solution matrix tracks the path taken

Variations:
- Allow movement in all 4 directions (up, down, left, right)
- Find the shortest path
- Count the number of possible paths
- Handle different maze sizes and configurations
*/


#include <iostream>
#include <vector>
using namespace std;

class RatInMaze {
private:
    int n;
    vector<vector<int>> maze;
    vector<vector<int>> solution;
    
    bool isSafe(int x, int y) {
        // Check if (x, y) is within bounds and is not blocked
        return (x >= 0 && x < n && y >= 0 && y < n && maze[x][y] == 1);
    }
    
public:
    void printSolution() {
        cout << "Solution Path:" << endl;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cout << solution[i][j] << " ";
            }
            cout << endl;
        }
        cout << endl;
    }
    RatInMaze(vector<vector<int>>& m) : maze(m), n(m.size()) {
        solution.resize(n, vector<int>(n, 0));
    }
    
    bool solveMaze(int x = 0, int y = 0) {
        // Base case: reached destination (bottom-right corner)
        if (x == n - 1 && y == n - 1 && maze[x][y] == 1) {
            solution[x][y] = 1;
            return true;
        }
        
        // Check if current position is valid
        if (isSafe(x, y)) {
            // Mark current cell as part of solution path
            solution[x][y] = 1;
            
            // Move right
            if (solveMaze(x, y + 1)) {
                return true;
            }
            
            // Move down
            if (solveMaze(x + 1, y)) {
                return true;
            }
            
            // If neither right nor down leads to solution, backtrack
            solution[x][y] = 0;
        }
        
        return false;
    }
    
    void findAllPaths(int x = 0, int y = 0) {
        // Base case: reached destination
        if (x == n - 1 && y == n - 1 && maze[x][y] == 1) {
            solution[x][y] = 1;
            printSolution();
            solution[x][y] = 0; // Backtrack for other solutions
            return;
        }
        
        // Check if current position is valid
        if (isSafe(x, y)) {
            // Mark current cell as part of solution path
            solution[x][y] = 1;
            
            // Try moving right
            findAllPaths(x, y + 1);
            
            // Try moving down
            findAllPaths(x + 1, y);
            
            // Backtrack: unmark current cell
            solution[x][y] = 0;
        }
    }
    
    void printMaze() {
        cout << "Given Maze:" << endl;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cout << maze[i][j] << " ";
            }
            cout << endl;
        }
        cout << endl;
    }
};

int main() {
    // Example maze: 1 represents open path, 0 represents blocked path
    vector<vector<int>> maze = {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {0, 1, 0, 0},
        {1, 1, 1, 1}
    };
    
    cout << "Rat in Maze Problem" << endl;
    cout << "===================" << endl;
    
    RatInMaze solver(maze);
    solver.printMaze();
    
    cout << "Finding one solution..." << endl;
    if (solver.solveMaze()) {
        cout << "Solution found!" << endl;
        solver.printSolution();
    } else {
        cout << "No solution exists!" << endl;
    }
    
    cout << "\nFinding all possible paths..." << endl;
    solver.findAllPaths();
    
    return 0;
}


