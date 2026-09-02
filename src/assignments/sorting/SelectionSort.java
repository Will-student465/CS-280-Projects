package assignments.sorting;

public class SelectionSort extends SortingAlgorithm {


    /**
     * Sort an array from left to right using selection sort
     * 
     * post -condition: array is sorted in ascending order
     * 
     * @param array of integers
     */

    public void sort(Integer[] array) {
        for (int k = 0; k < array.length - 1; k ++) {   // k is the index we place the smallest element in

            int minIndex = k;         // we assume the smallest element is at k
            for (int i = k + 1; i < array.length; i ++) { // Find smallest element and put it in minIndex
                if (array[i] < array[minIndex]) {
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

    private void swap(Integer[] array, int i, int j){
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }

    /** 
     * Run validation tests
     * @param args command line args
     */
    
    public static void main(String[] args){
        SortingAlgorithm.validate(new SelectionSort());
        System.out.println("SelectionSort has passed all tests.");
        }

}
