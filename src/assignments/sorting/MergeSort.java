package assignments.sorting;

import assignments.datastructures.Vector;


/**
 * Recursively Split data into subarrays and then merge them together in sorted order
 * 
 * @param <T> The type of element
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    /**  
     * Sort an array using mergeSort.
     * the array is copied into a new array for easy use and allocates another to use during merging
     * 
     * @param array the array to be sorted
     */
    public void sort (T[] array) {
        Vector<T> vector = new Vector<>();
        for (int i = 0; i < array.length; i++) {
            vector.insert(i, array[i]) ;

        }


        Vector<T> temp = new Vector<>();
        for (int i = 0; i < array.length; i++) {
            temp.insert(i, null);
        }

        mergeSort(vector, temp, 0, array.length - 1);

        // copies sorted array into user array
        for (int i = 0; i < array.length; i++){
            array[i] = vector.at(i);
        }
    }


    /**
     * Generates reqursive calls of mergeSort to split the array until each subarray has only one element,
     * then merges them back together
     * 
     * 
     * @param vector the array to be sorted
     * @param temp another array used to make merging easier
     * @param left starting index of the subarray
     * @param right last index of the subarray
     */
    private void mergeSort(Vector<T> vector, Vector<T> temp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // calls mergeSort on left and right ranges of the array
            mergeSort(vector, temp, left, mid);
            mergeSort(vector, temp, mid + 1, right);

            // merge only starts after recursive mergeSort calls end
            merge(vector, temp, left, mid, right);
        }
    }

    /**
     * merges the two sorted subarrays into a single sorted array
     * 
     * @param vector the array to be sorted
     * @param temp another array used to make merging easier
     * @param left starting index of the left subarray
     * @param mid last inxed of the left subarray
     * @param right last index of the right subarray
     */
    private void merge(Vector<T> vector, Vector<T> temp, int left, int mid, int right) {


        for (int i = left; i <= right; i++) {
            temp.set(i, vector.at(i));
        }


        int l = left; // the nex index to compare with m
        int m = mid + 1; // the index to compare to l
        int la = left; // the index to be modified next


        while (l <= mid && m <= right) {
            if (temp.at(l).compareTo(temp.at(m)) <= 0) {
                vector.set(la, temp.at(l));
                l++;
            }
            else {
                vector.set(la, temp.at(m));
                m++;
            }
            la++;
        }


        while (l <= mid) {
            vector.set(la, temp.at(l)) ;
            la++; 
            l++;
        }
    }


    /**
     * Run validation tests
     * @param args command line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort<>());
        System.out.println("MergeSort has passed all tests");
    }
    
}
