package assignments.sorting;

import assignments.datastructures.MinHeap;

public class HeapSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    public void sort(T[] array) {

        MinHeap<T> heap = new MinHeap<>();

        for (int i = 0; i < array.length; i++) {
            heap.enqueue(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
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
