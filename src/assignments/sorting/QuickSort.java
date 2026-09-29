package assignments.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Sort elements recursively relative to a pivot
 * 
 * @param <T> the type of each element
 */
public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    
    /**
     * call QuickSortalgorithm method and transfer the returned list's elements into the old list
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array){
        List<T> sorted = new ArrayList<>();
        for (T i : array) {
            sorted.add(i);
        }

        List<T> sortedlist = QuickSortalgorithm(sorted);

        for (int i = 0; i < sortedlist.size(); i ++) {
            array[i] = sortedlist.get(i);
        }
    }

    /**
     * Sort an array by choosing a pivot and assigning values to a left or right parition around it,
     * then recursively sort the paritions until list is sorted
     * 
     * @param array
     * @return a new list of sorted elements
     */
    private List<T> QuickSortalgorithm(List<T> array) {
        if (array.size() <= 1) {
            return array;
        }

        List<T> leftparition = new ArrayList<>();
        List<T> rightparition = new ArrayList<>();
        T pivot = array.get(0);


        for (int i = 1; i < array.size(); i++) {
            T temp = array.get(i);
            if (temp.compareTo(pivot) <= 0) {
                leftparition.add(temp);
            }
            else{
                rightparition.add(temp);
            }
        }

        List<T> LeftParitionSorted = QuickSortalgorithm(leftparition);
        List<T> RightParitionSorted = QuickSortalgorithm(rightparition);


        List<T> finalist = new ArrayList<>();

        finalist.addAll(LeftParitionSorted);
        finalist.add(pivot);
        finalist.addAll(RightParitionSorted);


        return finalist;

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

        