package graphs;

import java.util.Set;

public class AdjacencyMatrix {
    private boolean[][] matrix;

    /**
     * Construct an AdjacencyList representation of a graph with vertices V and edges E.
     * @param V a set of vertices, labeled with integers
     * @param E a set of edges
     * 
     * NOTE: In this assignment, the vertices will always be labeled from 0 up to n
     * 
     */
    public AdjacencyMatrix(Set<Integer> V, Set<Edge> E) {
        int n = V.size();
        this.matrix = new boolean[n][n];
        for (Edge e: E) {
            this.matrix[e.src][e.tgt] = true;
        }
    }

    /**
     * Report whether or not this graph has a given edge.
     * @param e the Edge
     */
    public boolean hasEdge(Edge e) {
        return matrix[e.src][e.tgt];
    }

    /**
     * Construct an AdjacencyMatrix representation of the graph G.
     * @param G the graph, as an AdjacencyList
     */
    public AdjacencyMatrix(AdjacencyList G) {
        // TODO implement this method
    }

    /**
     * Return a set of vertices in this graph.
     * 
     * Each vertex is represented by an integer.
     * 
     * @return Set<Integer>
     */
    public Set<Integer> getVertices() {
        // TODO implement this method
    }

    /**
     * Return a set of Edges in this graph.
     * @return Set<Edge>
     */
    public Set<Edge> getEdges() {
        // TODO implement this method
    }
}
