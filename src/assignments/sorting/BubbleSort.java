package assignments.sorting;

/*Swap ajacent elements over and over until the whole array is sorted*/
public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> 
{

    /**Sort an array in-place using bubble sort
     * 
     * Post -condition: array is sorted in ascending order
     *
     * @param array an array of integers
     */
    @Override
    public void sort (T[] array) {
        for (int k = array.length; k >= 2; k --)    // K is the length of the sub array we are looping through in the innter loop
            for (int i = 0; i < (k - 1); i++) {  //Go up to k - one, because we don't need to compare the last index against its neighbor
                if (array[i].compareTo(array [i+1]) > 0) {
                    /*Swap ajacent items */
                    swap(array, i, i+1);
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
     * Run validation texts.
     * @param args comman line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate (new BubbleSort<>());
        System.out.println("BubbleSort has passed all tests."); 
    }
}