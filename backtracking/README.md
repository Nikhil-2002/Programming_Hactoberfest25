# Backtracking Algorithms

This folder contains implementations of classic backtracking problems with detailed explanations and solutions.

## Problems Included

### 1. N-Queens Problem (`n_queens.cpp`)
- **Problem**: Place N queens on an N×N chessboard such that no two queens attack each other
- **Time Complexity**: O(N!)
- **Space Complexity**: O(N²)
- **Key Features**:
  - Finds one solution
  - Finds all possible solutions
  - Detailed safety checking for queen placement

### 2. Rat in Maze (`rat_in_maze.cpp`)
- **Problem**: Find a path from top-left to bottom-right corner in a maze
- **Time Complexity**: O(2^(n²)) in worst case
- **Space Complexity**: O(n²)
- **Key Features**:
  - Finds one solution path
  - Finds all possible paths
  - Handles blocked and open cells

### 3. Sudoku Solver (`sudoku_solver.cpp`)
- **Problem**: Solve a 9×9 Sudoku puzzle using backtracking
- **Time Complexity**: O(9^(n×n))
- **Space Complexity**: O(n×n)
- **Key Features**:
  - Validates Sudoku rules (row, column, 3×3 box)
  - Finds complete solution
  - Handles empty cells (represented by 0)

## How to Run

### Compilation
```bash
g++ -o n_queens n_queens.cpp
g++ -o rat_maze rat_in_maze.cpp
g++ -o sudoku_solver sudoku_solver.cpp
```

### Execution
```bash
./n_queens
./rat_maze
./sudoku_solver
```

## Backtracking Algorithm Pattern

All these problems follow the classic backtracking pattern:

1. **Choose**: Make a choice (place queen, move in maze, fill cell)
2. **Explore**: Recursively solve the subproblem
3. **Unchoose**: Backtrack if the choice doesn't lead to a solution

```cpp
bool solve(parameters) {
    if (base_case) {
        return true; // Solution found
    }
    
    for (each possible choice) {
        if (isValid(choice)) {
            makeChoice(choice);
            if (solve(updated_parameters)) {
                return true; // Solution found
            }
            undoChoice(choice); // Backtrack
        }
    }
    
    return false; // No solution found
}
```

## Key Concepts

- **Constraint Satisfaction**: Each problem has specific constraints that must be satisfied
- **State Space Tree**: The recursive calls form a tree of possible states
- **Pruning**: Invalid branches are pruned early to improve efficiency
- **Backtracking**: When a path doesn't lead to a solution, we undo the last choice

## Learning Outcomes

After studying these implementations, you'll understand:
- How to identify backtracking problems
- How to design recursive solutions with backtracking
- How to implement constraint checking
- How to optimize backtracking algorithms
- Common patterns in backtracking problems

## Extensions

You can extend these problems by:
- Adding more constraints
- Finding all solutions instead of just one
- Optimizing the algorithms further
- Implementing different variations of the problems
