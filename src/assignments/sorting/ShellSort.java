package assignments.sorting;

import java.util.Arrays;

/// Implements ShellSort using the Hibbard incrementation sequence.
/// The array is repeatedly sorted by comparing elements that 
/// are a fixed distance apart and shifting larger elements forward.
/// 
/// The gap decreases after each pass until it reaches 1,
/// and passes again to result in a fully sorted array.
/// 
/// @param <T> the type of elements
public class ShellSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sorts an array using ShellSort with the Hibbard incrementation sequence
     * 
     * post condition- the array is sorted
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {

        int hibbard = 1;

        while (2 * hibbard + 1 < array.length) { // finds the largest hibbard sequence number that can be used for the size of the array
            hibbard = 2 * hibbard + 1;
        }

        int j;
        
        for( int gap = hibbard  ; gap > 0; gap = (gap - 1) / 2) { // starts with largest usable Hibbard sequence number and decrements after each full pass
            for( int i = gap; i < array.length; i++) { // iterates through every element that has another element gap positions before it

                T temp = array[i]; // stors current value temporarily

                for ( j = i; j >= gap && temp.compareTo( array[j - gap]) < 0; j -= gap) array[j] = array[j - gap]; // Compares temp with elements that are gap indices behind it, then shifts the larger element forward by gap positions 

                array[j] = temp; // inserts temp into correct position
            }
        }
    }


    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new ShellSort<>());

        ShellSort<Integer> sort = new ShellSort<>();

        // Empty array
        Integer[] empty = {};
        sort.sort(empty);
        assert Arrays.equals(empty, new Integer[]{});

        //Single element
        Integer[] single = {42};
        sort.sort(single);
        assert Arrays.equals(single, new Integer[]{42});

        // Sorted Array
        Integer[] sorted = {1, 2, 3, 4, 5, 6, 7};
        sort.sort(sorted);
        assert Arrays.equals(sorted, new Integer[]{1, 2, 3, 4, 5, 6, 7});

        // Unsorted array
        Integer[] unsorted = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort.sort(unsorted);
         assert Arrays.equals(unsorted, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        // Duplicate elements
        Integer[] doubles = { 3, 3, 2, 5, 2, 5, 4, 8};
        sort.sort(doubles);
        assert Arrays.equals(doubles, new Integer[]{2, 2, 3, 3, 4, 5, 5, 8});

        // Negative numbers
        Integer[] negatives = {-9, 9, 8, 7, -6, -3};
        sort.sort(negatives);
        assert Arrays.equals(negatives, new Integer[]{-9, -6, -3, 7, 8, 9});

        // Huge Numbers
        Integer[] huge = {10000, 5000, 1000, -1000, -5000, -10000};
        sort.sort(huge);
        assert Arrays.equals(huge, new Integer[]{-10000, -5000, -1000, 1000, 5000, 10000});


        System.out.println("ShellSort passed all tests");
    }
}
