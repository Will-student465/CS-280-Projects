package assignments.mazes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Path implements Comparable<Path>, Iterable<Coordinate> {
    // Internal data structure tracking the path.
    private List<Coordinate> sequence;

    /**
     * Default constructor: an empty sequence.
     */
    public Path() {
        this.sequence = new ArrayList<>();
    }

    /**
     * Extend an existing path by one (creating a new Path).
     * 
     * @param core the existing path
     * @param next the coordinate to add
     */
    public Path(Path core, Coordinate next) {
        this.sequence = new ArrayList<>(core.sequence);
        this.sequence.add(next);
    }

    /**
     * Initialize a path to contain just one Coordinate.
     * @param only the coordinate to add
     */
    public Path(Coordinate only) {
        this();
        this.sequence.add(only);
    }

    /**
     * Paths are "higher priority", i.e. lesser in value, if they are shorter.
     * @param other a path to compare to
     * @return a negative number if this path is shorter than `other`
     */
    public int compareTo(Path other) {
        return this.sequence.size() - other.sequence.size();
    }

    /**
     * An iterator, enabling enhanced for loops over Paths:
     * 
     * for (Coordinate cell: Path) { ... }
     * 
     * @return the iterator
     */
    public Iterator<Coordinate> iterator() {
        return this.sequence.iterator();
    }

    /**
     * The last coordinate in the path.
     * @return the last coordinate, or null if the path is empty
     */
    public Coordinate terminus() {
        if (this.sequence.size() == 0) {
            return null;
        } else {
            return this.sequence.getLast();
        }
    }
}
