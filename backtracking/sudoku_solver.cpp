/*
Time Complexity: O(9^(n*n)) where n is the size of the board (9x9)
Space Complexity: O(n*n) for the recursion stack

Explanation:
Sudoku Solver is a classic backtracking problem where we need to fill a 9x9 grid
with digits 1-9 such that:
1. Each row contains all digits 1-9 exactly once
2. Each column contains all digits 1-9 exactly once  
3. Each 3x3 subgrid contains all digits 1-9 exactly once

Algorithm:
1. Find an empty cell (represented by 0)
2. Try placing digits 1-9 in that cell
3. Check if the placement is valid (no conflicts in row, column, or 3x3 box)
4. If valid, place the digit and recursively solve the rest
5. If no digit works, backtrack by removing the digit and try the next one
6. Continue until the entire board is filled or no solution exists

Key Functions:
- isValid(): Checks if placing a number at (row, col) violates Sudoku rules
- findEmptyCell(): Finds the next empty cell to fill
- solveSudoku(): Main recursive backtracking function

The algorithm uses backtracking to systematically explore all possible
number placements and find a valid solution.

Variations:
- Count the number of solutions
- Find all possible solutions
- Generate valid Sudoku puzzles
- Solve different grid sizes (4x4, 6x6, etc.)
*/


#include <iostream>
#include <vector>
using namespace std;



class SudokuSolver {
private:
    static const int SIZE = 9;
    vector<vector<int>> board;
    
    bool isValid(int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }
        
        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }
        
        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    bool findEmptyCell(int& row, int& col) {
        for (row = 0; row < SIZE; row++) {
            for (col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
public:
    SudokuSolver(vector<vector<int>>& puzzle) : board(puzzle) {}
    
    bool solveSudoku() {
        int row, col;
        
        // If no empty cell found, puzzle is solved
        if (!findEmptyCell(row, col)) {
            return true;
        }
        
        // Try numbers 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                // Place the number
                board[row][col] = num;
                
                // Recursively solve the rest
                if (solveSudoku()) {
                    return true;
                }
                
                // Backtrack: remove the number
                board[row][col] = 0;
            }
        }
        
        return false;
    }
    
    void printBoard() {
        cout << "Sudoku Board:" << endl;
        cout << "-------------" << endl;
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                cout << "-------------" << endl;
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    cout << "| ";
                }
                cout << board[i][j] << " ";
            }
            cout << endl;
        }
        cout << "-------------" << endl;
    }
    
    bool isComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
};

int main() {
    // Example Sudoku puzzle (0 represents empty cells)
    vector<vector<int>> puzzle = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
    
    cout << "Sudoku Solver" << endl;
    cout << "=============" << endl;
    
    SudokuSolver solver(puzzle);
    
    cout << "Initial puzzle:" << endl;
    solver.printBoard();
    
    if (solver.solveSudoku()) {
        cout << "\nSolution found!" << endl;
        solver.printBoard();
        
        if (solver.isComplete()) {
            cout << "Puzzle is completely solved!" << endl;
        }
    } else {
        cout << "\nNo solution exists for this puzzle!" << endl;
    }
    
    return 0;
}

