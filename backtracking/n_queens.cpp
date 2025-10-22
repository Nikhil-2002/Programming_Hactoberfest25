/*
Time Complexity: O(N!)
Space Complexity: O(N^2)

Explanation:
The N-Queens problem is a classic backtracking problem where we need to place N queens 
on an N×N chessboard such that no two queens attack each other.

Algorithm:
1. Start with the first row and try to place a queen in each column
2. For each placement, check if it's safe (no conflicts with previously placed queens)
3. If safe, place the queen and recursively solve for the next row
4. If we reach the last row successfully, we found a solution
5. If not, backtrack by removing the queen and try the next column
6. Continue until all solutions are found

The isSafe function checks:
- Column conflicts: No other queen in the same column
- Diagonal conflicts: No other queen in the upper left and upper right diagonals

This approach uses backtracking to systematically explore all possible placements
and find valid solutions.
*/
#include <iostream>
#include <vector>
using namespace std;

class NQueens {
private:
    int n;
    vector<vector<int>> board;
    
    bool isSafe(int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }
        
        // Check upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        
        // Check upper right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        
        return true;
    }
    
    void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cout << (board[i][j] == 1 ? "Q " : ". ");
            }
            cout << endl;
        }
        cout << endl;
    }
    
public:
    NQueens(int size) : n(size) {
        board.resize(n, vector<int>(n, 0));
    }
    
    bool solveNQueens(int row = 0) {
        // Base case: all queens are placed
        if (row == n) {
            printBoard();
            return true;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                // Place queen
                board[row][col] = 1;
                
                // Recursively place queens in remaining rows
                if (solveNQueens(row + 1)) {
                    return true; // Found a solution
                }
                
                // Backtrack: remove queen
                board[row][col] = 0;
            }
        }
        
        return false; // No solution found
    }
    
    void findAllSolutions(int row = 0) {
        // Base case: all queens are placed
        if (row == n) {
            printBoard();
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                // Place queen
                board[row][col] = 1;
                
                // Recursively place queens in remaining rows
                findAllSolutions(row + 1);
                
                // Backtrack: remove queen
                board[row][col] = 0;
            }
        }
    }
};

int main() {
    int n;
    cout << "Enter the number of queens (board size): ";
    cin >> n;
    
    if (n <= 0) {
        cout << "Invalid input! Please enter a positive integer." << endl;
        return 1;
    }
    
    NQueens solver(n);
    
    cout << "\nOne solution for " << n << " queens problem:" << endl;
    if (!solver.solveNQueens()) {
        cout << "No solution exists for " << n << " queens!" << endl;
    }
    
    cout << "\nAll solutions for " << n << " queens problem:" << endl;
    solver.findAllSolutions();
    
    return 0;
}


