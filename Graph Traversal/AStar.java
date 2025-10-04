import java.util.*;

/**
 * Implements the A* (A-star) search algorithm for finding the shortest path
 * between two nodes in a graph. It is an informed search algorithm that uses a
 * heuristic to guide its search towards the goal.
 *
 * This example uses a grid-based map.
 */
public class AStar {

    // Inner class for a Node in the search space
    static class Node implements Comparable<Node> {
        int x, y; // Coordinates on the grid
        int g;    // Cost from the start node to this node
        int h;    // Heuristic: estimated cost from this node to the end node
        int f;    // Total cost: f = g + h
        Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // Heuristic function (Manhattan distance)
    private int heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    /**
     * Finds the shortest path from start to end using A* algorithm.
     * @param grid The grid representing the map (0=walkable, 1=obstacle).
     * @param start The starting node.
     * @param end The ending node.
     * @return A list of nodes representing the path, or null if no path is found.
     */
    public List<Node> findPath(int[][] grid, Node start, Node end) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        start.g = 0;
        start.h = heuristic(start, end);
        start.f = start.g + start.h;
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(end)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(grid, current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeG = current.g + 1; // Assuming cost of 1 for each step

                if (tentativeG < neighbor.g || !openSet.contains(neighbor)) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = heuristic(neighbor, end);
                    neighbor.f = neighbor.g + neighbor.h;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return null; // No path found
    }

    private List<Node> getNeighbors(int[][] grid, Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int newX = node.x + dx[i];
            int newY = node.y + dy[i];

            if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 0) {
                neighbors.add(new Node(newX, newY));
            }
        }
        return neighbors;
    }

    private List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Main method to test the A* implementation.
     */
    public static void main(String[] args) {
        // 0 = walkable, 1 = obstacle
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };

        Node start = new Node(0, 0);
        Node end = new Node(4, 4);

        AStar astar = new AStar();
        List<Node> path = astar.findPath(grid, start, end);

        if (path != null) {
            System.out.println("A* Search: Path found!");
            for (Node node : path) {
                System.out.print("(" + node.x + ", " + node.y + ") -> ");
            }
            System.out.println("END");
        } else {
            System.out.println("No path found.");
        }
    }
}