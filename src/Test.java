import assignments.datastructures.*;
import assignments.sorting.*;
import assignments.datastructures.KeyValuePair;

public class Test {
    public static void main(String[] args) {
        if (!checkAssertions()) {
            System.err.println("""
            ERROR: Some tests rely on assertions. Please run java with the -ea option, e.g.

                java -cp lib/* -ea src/Test.java
            """);
            System.exit(1);
        }

        assert runTests(args);
        System.out.println("All tests have passed without error.");
        System.exit(0);
    }

    private static boolean checkAssertions() {
        try {
            assert false;
        } catch (AssertionError e) {
            return true;
        }
        return false;
    }

    private static boolean runTests(String[] args) {
        // Test sorting algorithms.
        /* Call additional main routines here as you create new sorting algorithms. */
        BubblesSort.main(args); // BubblesSort test
        InsertionSort.main(args); // InsertionSort test
        
        // Test data structures.
        /* Call additional main routines as you create new data strutures. */
        KeyValuePair.main(args);

        return true;
    }
}
