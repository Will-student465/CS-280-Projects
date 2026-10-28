package assignments.sorting;

import java.util.Iterator;

import assignments.datastructures.BinarySearchTree;

import assignments.datastructures.Vector;

public class TreeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    
    /**
     * Sorts an array by adding each element to a Binary Search tree and then iterating them back into the array "inorder" which is sorted order
     * 
     * @param array the array to be sorted
     */
    public void sort(T[] array) {

        BinarySearchTree<T> bst = new BinarySearchTree<>(); // allocates a new Binary Search Tree

        for (int i = 0; i < array.length; i++) { // adds all elements to the tree

            bst.add(array[i]);
        }


        Iterator<T> sorted = bst.inorder(); // creates the inorder iterator

        int i = 0;
        while (sorted.hasNext() && i < array.length) { // adds each element the iterator passes over into its sorted position in the array
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
