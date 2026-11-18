package assignments.mazes;

import org.w3c.dom.Element;

public class Coordinate {
    public final int r;    // Row index.
    public final int c;    // Column index.

    /**
     * Encapsulate a row and column index.
     * 
     * @param r row index
     * @param c column index
     */
    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }

    /**
     * Convert an XML element into a coordinate.
     * 
     * @param node DOM node of an element with `row` and `col` attributes
     * @throws IllegalArgumentException if node does not have the required attributes with valid data
     */
    public Coordinate(Element node) {
        String row = node.getAttribute("row");
        String col = node.getAttribute("col");

        // Check that row and col attributes exist.
        if (row == "" || col == "") {
            throw new IllegalArgumentException(
                "Coordinate element must have `row` and `col` attributes.\n" +
                "Violating cell: "+node
            );
        }

        // Check that row and col attributes are integers.
        int r, c;
        try {
            r = Integer.parseInt(row);
            c = Integer.parseInt(col);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Coordinate elements' `row` and `col` attributes must be integers.\n" +
                "Violating cell: ("+row+","+col+")"
            );
        }

        // VALIDATION: row and col must be nonnegative.
        if (r < 0 || c < 0) {
            throw new IllegalArgumentException(
                "Row and column must be nonnegative.\n" +
                "Violating cell: ("+row+","+col+")"
            );
        }

        this(r,c);
    }

    /**
     * Render the coordinate as an ordered pair.
     * 
     * @return a string of the form (r,c)
     */
    public String toString() {
        return "("+this.r+","+this.c+")";
    }

    /**
     * Compare equality between coordinates.
     * 
     * Necessary to ensure correct behavior when used in a `Map` data structure.
     * 
     * @param o another coordinate
     */
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate)) return false;
        Coordinate co = (Coordinate)o;
        return this.r == co.r && this.c == co.c;
    }

    /**
     * Map coordinates to integers, such that equal coordinates map to equal integers.
     * 
     * Necessary to ensure correct behavior when used in a `Map` data structure.
     * 
     * @return an integer which is unique for every coordinate, so long as the numbers don't get too big
     */
    public int hashCode() {
        return (r<<16) + c;
    }
}