package assignments.sorting;

import assignments.datastructures.Vector;

/**
 * Sort elements recursively relative to a pivot
 * 
 * @param <T> the type of each element
 */
public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    
    /**
     * copies given array into a new vector for easier access
     * then calls quickSortAlgorithm and copies sorted array into given array once completed
     *  
     * @param array the array to be sorted
     */
    public void sort(T[] array){
        Vector<T> vector = new Vector<T>();


        for (int i = 0; i < array.length; i++) {
            vector.insert(i, array[i]);
        }


        quickSortAlgorithm(vector, 0, vector.length() -1);


        for (int i = 0; i < array.length; i++) {
            array[i] = vector.at(i);
        }
    }

    /**
     * recursively sorts the portion of the vector that is between left and right indexes. 
     * paritions returns the index of the element used as "pivot" in its method to define the next subarrays to be sorted.
     * repeats until each subarray has only one element and the sort process is completed
     * 
     * 
     * @param vector the array to be sorted
     * @param left the leftmost side of the working portion of the array
     * @param right the rightmost side of the working portion of the array
     */
    private void quickSortAlgorithm(Vector<T> vector, int left, int right) {
        if (left < right){
            int pivotNum = partitions(vector, left, right);
            quickSortAlgorithm(vector, left, pivotNum - 1);
            quickSortAlgorithm(vector, pivotNum + 1, right);
        }
    }

    /**
     * partitions between the given indexes left and right in the vector using the middle index as the pivot.
     * 
     * 
     * @param vector the array to be sorted
     * @param left the leftmost side of the working portion of the array
     * @param right the righmost side of the working portion of the array
     * @return the index of the pivot after partitioning, separating the left and right partitions for recursive calls.
     */
    private int partitions(Vector<T> vector, int left, int right) {
        int mid = (left + right)/2; // choses the middle value to make worst-case runtimes less likely
      
        swaps(vector, mid, right);
        int j = left;
        T pivot = vector.at(right);
        


        for (int i = left; i < right; i++) {
            if (vector.at(i).compareTo(pivot) <= 0) {
                swaps(vector,j,i);
                j++;
            }

        }
        
        swaps(vector, j, right);
        return j;
    }


    /**
     * swaps two elements at given indexes inside our vector
     * 
     * @param vector the array to be sorted
     * @param a the index of the first element
     * @param b the index of the second element
     */
    private void swaps(Vector<T> vector, int a, int b) {
        T temp = vector.at(a);
        vector.set(a, vector.at(b));
        vector.set(b, temp);
    }


    /**
     * Run validation tests
     * @param args command line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new QuickSort<>());
        System.out.println("QuickSort has passed all tests");
    }

}
