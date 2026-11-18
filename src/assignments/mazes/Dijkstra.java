package assignments.mazes;

public class Dijkstra {
    public static void main(String[] args) throws Exception {
        // Select your maze.
        /* TODO: you can change this path once you add your own! */
        String path = "Mazes/Mymaze.xml";

        // Load the map from XML into internal memory.
        Maze maze = new Maze(path);

        // Render it to the screen.
        System.out.println(maze);

        // Solve the maze with Dijkstra's algorithm.
        Path solution = maze.solve();
        
        // Render the solution to the screen.
        if (solution == null) {
            System.out.println("No solution could be found.");
        } else {
            char[][] rendering = maze.makePathMatrix(solution);
            System.out.println(Maze.render(rendering));
        }
    }
}
