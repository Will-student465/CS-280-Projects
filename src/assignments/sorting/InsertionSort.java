package assignments.sorting;


/** Sort an array in place using insertion sort
 * 
 * post -condition: array is sorted in ascending order
 */

public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T>{

    /**@param array an array of "Comparable" elements*/

    public void sort (T[] array) {
        for (int k = 1; k < array.length; k ++)  // k is the index of the element we are holding
            for (int i = k; i > 0; i --) {  // i compares backwards from k into the sorted portion
                if (array[i].compareTo(array[i-1])< 0) { // compares backwards and swaps if i is less than i - 1
                    swap(array, i, i-1); 
                } 
                else {
                    break; // break here because element is already in the right placce
                }

            }
        }
    
    /** 
     * Swap two elements within an array
     * 
     * @param array the array to swap values in
     * @param i the first index to swap
     * @param j the second index to swap
     * 
     */

    private void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Run validation tests
     * @param args command line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort<>());
        System.out.println("InsertionSort has passed all tests.");

        
    }
}

