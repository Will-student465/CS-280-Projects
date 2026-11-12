package assignments.sorting;

import java.util.Arrays;

import assignments.datastructures.Vector;


/**
 * Sorts integers using Radix Sort.
 * 
 * separates negative and positive integers to be sorted separately 
 * before reconstructing a sorted array.
 */
public class RadixSort extends SortingAlgorithm<Integer>  {
    

    /** 
     * Sorts an array of integers using Radix Sort
     * 
     * Negative and positive values are sorted separatly.
     * 
     * negative values are converted to absolute value for sorting,
     * then are restored to their negative value after sorting.
     * 
     * @param array the array to be sorted
     */
    public void sort(Integer[] array) {

        Vector<Integer> negative = new Vector<>();

        Vector<Integer> positive = new Vector<>();

        // Separate negative and positive integers into Vectors to find size.
        // Take the absolute value of negative integers
        for (int v : array) {  
            if (v < 0) {
                negative.push(-v);
            }
            else {
                positive.push(v);
            }
        }

        // move all elements to sortable arrays
        Integer[] nArray = new Integer[negative.length()];

        Integer[] pArray = new Integer[positive.length()];

        for (int i = 0; i < negative.length(); i++) {
            nArray[i] = negative.at(i);
        }

        for (int i = 0; i < positive.length(); i++) {
            pArray[i] = positive.at(i);
        }

        // call sorting algorithm
        sortPositive(nArray);
        sortPositive(pArray);

        
        int index = 0;

        
        // values from the nArray are stored back into the original array in reverse order
        // to account for their return to negative.
        for (int i = nArray.length - 1; i >= 0; i--) {
            array[index++] = -nArray[i];
        }

        // values from pArray are stored back into the original array in sorted order.
        for (int i = 0; i < pArray.length; i++) {
            array[index++] = pArray[i];
        }

    }


    /**
     * Sorts an array using Radix Sort.
     * Uses a bucket sort pass on each numbers position of elements.
     * 
     * @param array the array to be sorted
     */
    private void sortPositive(Integer[] array) {

        if (array.length <= 1) {
            return;
        }


        int max = array[0];

        for (int v : array) {
            if (v > max) {
                max = v;
            }
        }

        for (int place = 1; max / place > 0; place *= 10) {
            mainAlgorithm(array, place);
        }
    }


    /**
     * Preforms bucket sort pass on a single numbers position.
     * 
     * @param array the array to be sorted
     * @param place the numbers position to sort by
     */
    private void mainAlgorithm (Integer[] array, int place) {
        int length = array.length;

        int[] output = new int[length];

        int[] count = new int[10];

        
        for (int i = 0; i < array.length; i++) { // counts each occurrence of a digit
            int digit = (array[i] / place) % 10;
            count[digit]++;
        }


        for (int i = 1; i < 10; i++) { // converts count into a cumulative count
            count[i] += count[i - 1];
        }


        for (int i = length - 1; i >= 0; i--) { // builds output array

            int digit = (array[i] / place) % 10;

            output[count[digit] - 1] = array[i];

            count[digit]--;
        }


        for (int i = 0; i < length; i++) {
            array[i] = output[i];
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new RadixSort());

        RadixSort sort = new RadixSort();

        // Test empty
        Integer[] empty = {};
        sort.sort(empty);
        assert Arrays.equals(empty, new Integer[]{});

        // Test single element
        Integer[] lonely = {13};
        sort.sort(lonely);
        assert Arrays.equals(lonely, new Integer[]{13});

        // Test positive
        Integer[] positive = {10, 8, 7, 5, 4, 3, 2, 1};
        sort.sort(positive);
        assert Arrays.equals(positive, new Integer[]{1, 2, 3, 4, 5, 7, 8, 10});

        // Test negative
        Integer[] negative = {-1, -4, -5, -6, -7, -8, -10};
        sort.sort(negative);
        assert Arrays.equals(negative, new Integer[]{-10, -8, -7, -6, -5, -4, -1});

        // test normal
        Integer[] normal = {15, 10, 5, 0, -5, -10, -15};
        sort.sort(normal);
        assert Arrays.equals(normal, new Integer[]{-15, -10, -5, 0, 5, 10, 15});

        // Test huge numbers
        Integer[] huge = {1000, 10000, -20000, -1000, 50000, -50000};
        sort.sort(huge);
        assert Arrays.equals(normal, new Integer[]{-15, -10, -5, 0, 5, 10, 15});

        // Test duplicate elements
        Integer[] duplicate = {4, 4, 5, 2, 4, 2, 5, 8};
        sort.sort(duplicate);
        assert Arrays.equals(duplicate, new Integer[]{2, 2, 4, 4, 4, 5, 5, 8});

        System.out.println("RadixSort passed all tests");
    }
}
