package assignments.sorting;

import assignments.datastructures.Vector;

///Implements ShellSort using the Hibbard incrementation sequence.
/// Sorts a list by comparing and swapping elements that are distant from each other 
/// then decreases the distance between compared elements and repeats
/// finishes by comparing adjacent elements resulting in a sorted array
/// 
/// @param <T> the type of elements
public class ShellSort<T extends Comparable<T>> extends Vector<T> {
    

    public void Sort(T[] array) {

        int hibbard = 1;

        while (2 * hibbard + 1 < array.length) {
            hibbard = 2 * hibbard + 1;
        }

        int j;
        
        for( int gap = hibbard  ; gap > 0; gap = (gap - 1) / 2) {
            for( int i = gap; i < array.length; i++) {

                T temp = array[i];
                for ( j = i; j >= gap && temp.compareTo( array[j - gap]) < 0; j -= gap) array[j] = array[j - gap];

                array[j] = temp;
            }
        }
    }
}
