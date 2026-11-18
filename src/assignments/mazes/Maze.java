package assignments.mazes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Maze {
    private Coordinate start;   // entrance for the maze
    private Coordinate end;     // exit for the maze
    private Map<Coordinate,List<Coordinate>> edges;     
        // adjacency "list". We use a "Map" data structure so that the "index" can be a generic type rather than only an integer.

    private int maxRow;      // Total width of maze.
    private int maxCol;      // Total height of maze.

    /**
     * Parse an XML file into a Maze.
     * 
     * @param xmlPath the path to the XML file
     * @throws IllegalArgumentException if xmlPath does not load, or if the file is not correctly formatted
     * 
     */
    public Maze(String xmlPath) throws IllegalArgumentException {
        Element mazeNode;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder(); // may throw ParserConfigurationException
            Document doc = documentBuilder.parse(xmlPath); // may throw IOException, SAXException, or IllegalArgumentException
            mazeNode = doc.getDocumentElement();
        } catch(ParserConfigurationException e) {
            throw new IllegalArgumentException(
                "Failed to configure default parser? That doesn't even make sense...\n" +
                e.getMessage()
            );
        } catch(IOException e) {
            throw new IllegalArgumentException(
                "Failed to load your xml path.\n" +
                e.getMessage()
            );
        } catch(SAXException e) {
            throw new IllegalArgumentException(
                "Improperly formatted file.\n" +
                e.getMessage()
            );
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Undefined xml path.\n" +
                e.getMessage()
            );
        }


        // REGISTER START ELEMENT
        // Fetch all <start> elements. There should only be one.
        NodeList startNodes = mazeNode.getElementsByTagName("start");
        if (startNodes.getLength() != 1) {
            throw new IllegalArgumentException("Maze must have exactly one <start> element.");
        }
        this.start = new Coordinate((Element)startNodes.item(0));

        // REGISTER END ELEMENT
        // Fetch all <end> elements. There should only be one.
        NodeList endNodes = mazeNode.getElementsByTagName("end");
        if (endNodes.getLength() != 1) {
            throw new IllegalArgumentException("Maze must have exactly one <end> element.");
        }
        this.end = new Coordinate((Element)endNodes.item(0));

        // REGISTER EDGES
        // Initialize the adjacency list to be empty. We'll add vertices as we go.
        this.edges = new HashMap<>();
        // Fetch all <cell> elements.
        NodeList cellNodes = mazeNode.getElementsByTagName("cell");
        for (int i = 0; i < cellNodes.getLength(); i ++) {
            Element cellNode = (Element)cellNodes.item(i);

            // REGISTER EACH VERTEX
            Coordinate cell = new Coordinate(cellNode);
            
            // Initialize the list for each vertex to be empty.
            this.edges.put(cell, new ArrayList<>());    
            // Fetch all <nextto> elements.
            NodeList nextTos = cellNode.getElementsByTagName("nextto");
            for (int j = 0; j < nextTos.getLength(); j ++) {
                Element nextToNode = (Element)nextTos.item(j);

                // REGISTER EACH EDGE
                Coordinate nextTo = new Coordinate(nextToNode);
                this.edges.get(cell).add(nextTo);

                // VALIDATION: Each edge should be between adjacent elements.
                if (Math.abs(nextTo.r - cell.r) + Math.abs(nextTo.c - cell.c) != 1) {
                    throw new IllegalArgumentException(
                        "All edges must be between adjacent cells.\n"+
                        "Cell "+cell+" cannot be next to "+nextTo+"."
                    );
                }
            }
        }

        // VALIDATION: All edges have reciprocals (the maze should be an undirected graph).
        for (Coordinate cell: edges.keySet()) {
            for (Coordinate nextto: edges.get(cell)) {
                // Bordering node must be in the graph, and eplicitly border the cell.
                if (!(edges.containsKey(nextto) && edges.get(nextto).contains(cell))) {
                    throw new IllegalArgumentException(
                        "All edges must be two-way.\n" +
                        "Cell "+cell+" borders "+nextto+" but not vice versa."
                    );
                }
            }
        }

        // Compute the dimensions of the maze.
        this.maxRow = Math.max(start.r, end.r);
        this.maxCol = Math.max(start.c, end.c);
        for (Coordinate cell: edges.keySet()) {
            this.maxRow = Math.max(maxRow, cell.r);
            this.maxCol = Math.max(maxCol, cell.c);
        }

        // VALIDATION: start and end must be on the edge of the maze.
        if (!(start.r == 0 || start.c == 0 || start.r == maxRow || start.c == maxCol)) {
            throw new IllegalArgumentException(
                "<start> element must touch the wall of the maze.\n"+
                "Starting cell "+start+", maxRow="+maxRow+", maxCol="+maxCol
            );
        }
    }

    /**
     * Render the maze with all its walls.
     * 
     * @return a multi-line string rendering of the maze with all its walls
     */
    public String toString() {
        return render(makeWallMatrix());
    }

    /**
     * Render a maze represented with a matrix of characters.
     * 
     * @param maze a 2d array of characters, such that maze[i] is the ith line to render
     */
    public static String render(char[][] maze) {
        String rendered = "";
        for (char[] line: maze) {   // for each row
            for (char x: line) {    // for each col
                rendered += x;
            }
            rendered += "\n";
        }
        return rendered;
    }

    /**
     * Prepare a matrix of characters rendering a maze with all its walls.
     * 
     * This matrix can be subsequently modified to draw a path.
     * 
     * @return a 2d array of characters, such that maze[i] is the ith line to render
     */
    public char[][] makeWallMatrix() {
        // Every cell has walls on all sides. Interior walls are shared. That means 1+2n lines to render n rows.
        // Number of rows n = 1+maxRow, so total height = 1+2*(1+maxRow)
        // Index convention: maze[i] is ith row, maze[i][j] is ith row, jth column
        char[][] maze = new char[1+2*(1+maxRow)][1+2*(1+maxCol)];

        // Initialize the maze to be completely filled in.
        for (int r = 0; r < maze.length; r ++) {            // for each row
            for (int c = 0; c < maze[0].length; c ++) {     // for each col
                maze[r][c] = '|';
            }
        }

        for (Coordinate cell: edges.keySet()) {
            // Get matrix coordinate of the active cell vertex.
            int r = 1+2*cell.r;
            int c = 1+2*cell.c;

            // Empty out cells.
            maze[r][c] = ' ';

            for (Coordinate nextto: edges.get(cell)) {
                // Empty out edges.
                maze[r + (nextto.r-cell.r)][c + (nextto.c-cell.c)] = ' ';
            }
        }

        // Poke holes in the wall for the starting point.
        int sr = 1+2*start.r;
        int sc = 1+2*start.c;
        if (start.r == 0) {
            maze[sr-1][sc] = 'v';
        } else if (start.c == 0) {
            maze[sr][sc-1] = '>';
        } else if (start.r == maxRow) {
            maze[sr+1][sc] = '^';
        } else { // (start.c == maxCol) guaranteed
            maze[sr][sc+1] = '<';
        }

        // Poke holes in the wall for the ending point.
        int er = 1+2*end.r;
        int ec = 1+2*end.c;
        if (end.r == 0) {
            maze[er-1][ec] = '^';
        } else if (end.c == 0) {
            maze[er][ec-1] = '<';
        } else if (end.r == maxRow) {
            maze[er+1][ec] = 'v';
        } else { // (end.c == maxCol) guaranteed
            maze[er][ec+1] = '>';
        }

        return maze;
    }

    /**
     * Prepare a matrix of characters rendering a maze with all its walls, then trace a path.
     * 
     * @param path a contiguous Path through the maze - Coordinates adjacent in the path should be directly connected in the maze
     * @return a 2d array of characters, such that maze[i] is the ith line to render
     */
    public char[][] makePathMatrix(Path path) {
        char[][] maze = makeWallMatrix();
        
        Coordinate cell = null;     // starts null in first iteration, but then serves as the `source`.
        for (Coordinate nextto: path) {
            if (cell != null) { // Skip the first iteration, when we have no `source` vertex yet.
                // Get matrix coordinate of the active cell vertex.
                int r = 1+2*cell.r;
                int c = 1+2*cell.c;

                // Draw a path on the source cell (redundant except in first iteration).
                maze[r][c] = '*';
                // Draw a path between the source and target cell.
                maze[r + (nextto.r-cell.r)][c + (nextto.c-cell.c)] = '*';
                // Draw a path on the target cell.
                maze[r + 2*(nextto.r-cell.r)][c + 2*(nextto.c-cell.c)] = '*';
            }

            // Iterate so the current `target` is set to the `source`.
            cell = nextto;
        }

        return maze;
    }

    /**
     * Find the shortest path from `start` to `end`.
     * 
     * @return a `Path` whose first element is `start`, last element is `end`, and every adjacent `Coordinate` on the `Path` is connected.
     * 
     */
    public Path solve() {
        // Initialize the queue of paths to visit with one containing just the starting point.
        PriorityQueue<Path> queue = new PriorityQueue<>();
        queue.add(new Path(start));

        Set<Coordinate> checked = new HashSet<>();

        // Take off the next shortest path as long as there is one.
        while (!queue.isEmpty()) {
        /* Hint: Indentation is a clue... */


            // Get the next shortest path.
            Path path = queue.poll();
            Coordinate cursor = path.terminus();

            // Base case: have we found the exit?
            if (cursor.equals(end)) {
                return path;
            }

            // Base case: have we already visited this node?
            if (checked.contains(cursor)) {
                continue;
            }

            checked.add(cursor);

            // Add all possible next coordinates to our list of nodes to visit.
            for (Coordinate i : edges.get(cursor)) {
                if (!checked.contains(i)) {
                    queue.add(new Path(path, i));
                }
            }
        }

        // If we've run out of paths to visit, the start and end must be disconnected.
        return null;
    }
    
}
