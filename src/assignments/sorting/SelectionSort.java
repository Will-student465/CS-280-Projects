package assignments.sorting;

/**
 * Sort a list by repeatedly finding the smallest unsorted element and swapping it into the correct position
 * 
 * @param <T> the type of each element 
 */
public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {


    /**
     * Sort an array in place using selection sort
     * 
     * post -condition: array is sorted in ascending order
     * 
     * @param array an array of integers
     */

    public void sort(T[] array) {
        for (int k = 0; k < array.length - 1; k ++) {   // k is the index we place the smallest element in

            int minIndex = k;         // we assume the smallest element is at k
            for (int i = k + 1; i < array.length; i ++) { // Find smallest element and put it in minIndex
                if (array[i].compareTo(array[minIndex])< 0) {
                    minIndex = i;
                }
            }
            swap(array, k, minIndex); // swap minIndex into index k

        } 

    }

    /**
     * Swap two elements within an array
     * 
     * @param array the array to swap values in
     * @param i the first index to swap
     * @param j the second index to swap
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
    
    public static void main(String[] args){
        SortingAlgorithm.validate(new SelectionSort<>());
        System.out.println("SelectionSort has passed all tests.");
        }

}
