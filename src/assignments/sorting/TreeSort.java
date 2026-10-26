package assignments.sorting;

import java.util.Iterator;

import assignments.datastructures.BinarySearchTree;

import assignments.datastructures.Vector;

public class TreeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    

    public void sort(T[] array) {

        BinarySearchTree<T> bst = new BinarySearchTree<>();

        for (int i = 0; i < array.length; i++) {

            bst.add(array[i]);
        }


        Iterator<T> sorted = bst.inorder();

        int i = 0;
        while (sorted.hasNext() && i < array.length) {
            array[i++] = sorted.next();
        }
    }


    /**
     * Run validation texts.
     * @param args comman line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate (new TreeSort<>());
        System.out.println("TreeSort has passed all tests."); 
    }
}
