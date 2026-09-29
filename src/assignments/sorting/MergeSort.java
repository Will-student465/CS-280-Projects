package assignments.sorting;

import java.util.ArrayList;
import java.util.List;


/**
 * Split data into subclasses and then recursively merge them together
 * 
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {


    public void sort (T[] array) {
        List<T> sorted = new ArrayList<>();
        for (T i : array) {
            sorted.add(i);
        }

        List<T> sortedList = Split(sorted);

        for (int i = 0; i < sortedList.size(); i ++) {
            array[i] = sortedList.get(i);
        }
    }


    /**
     * Split the array into many arrays of length one with recursion
     * 
     * then calls Merge method
     * 
     * @param array The list to be split
     * @return a call to Merge method
     */
    private List<T> Split(List<T> array) {
        if (array.size() <= 1) {
            return array;
        }
        

        int mid = array.size() / 2;


        List<T> leftparition = new ArrayList<>();
        List<T> rightparition = new ArrayList<>();


        for (int i = 0; i < mid; i++) {
            leftparition.add(array.get(i));
        }
        for (int i = mid; i < array.size(); i++) {
            rightparition.add(array.get(i));
        }

        List<T> sortedL = Split(leftparition);
        List<T> sortedR = Split(rightparition);

        return Merge(sortedL, sortedR);
    }


    /**
     * Merge two lists and sort them into one
     * 
     * 
     * @param left The left parition to be merged and sorted
     * @param right The right parition to be merged and sorted
     * @return finalist the merged and sorted list
     */
    private List<T> Merge(List<T> left, List<T> right) {
        List<T> finalist = new ArrayList<>();

        int i = 0;
        int j = 0;
        

        while (i < left.size() && j < right.size()){
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                finalist.add(left.get(i));
                i++;
            }
            else{
                finalist.add(right.get(j));
                j++;
            }
        }


        for (int t = i; t < left.size(); t++){
            finalist.add(left.get(t));
        }
        for (int y = j; y < right.size(); y++) {
            finalist.add(right.get(y));
        }

        return finalist;
    }





    /**
     * Run validation tests
     * @param args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort<>());
        System.out.println("MergeSort has passed all tests");
    }
    
}
