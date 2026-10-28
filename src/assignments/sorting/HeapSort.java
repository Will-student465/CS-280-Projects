package assignments.sorting;

import assignments.datastructures.MinHeap;

public class HeapSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /** 
     * Sorts an array by enqueing all elements to a MinHeap and then dequeuing all elements back into array
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {

        MinHeap<T> heap = new MinHeap<>(); // allocate new MinHeap

        for (int i = 0; i < array.length; i++) { // enqueue operations
            heap.enqueue(array[i]);
        }

        for (int i = 0; i < array.length; i++) { // dequeue operations
            array[i] = heap.dequeue();
        }
    }


    /**
     * Run validation texts.
     * @param args comman line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate (new HeapSort<>());
        System.out.println("HeapSort has passed all tests."); 
    }
}
