package assignments.datastructures;

import adt.Set;

import java.util.Iterator;

/**
 * A mathematical set backed by a Binary Search Tree.
 * No duplicate elements may be stored.
 * 
 * 
 * @param <T> the type of each element
 */
public class TreeSet<T extends Comparable<T>> implements Set<T> {

    // Initialize an empty BinarySearchTree
    BinarySearchTree<T> bst = new BinarySearchTree<>();


    /**
     * Adds an element to the set if the set does not already contain it.
     * 
     * @param thing the element to be added
     * @return true if the element is added, otherwise false
     */
    public boolean add(T thing) {

        if (contains(thing)) return false;

        bst.add(thing);
        
        return true;
    }


    /** 
     * Determines whether the set contains an element.
     * 
     * @param thing the element to be searched for
     * @return true if the element is in the set, otherwise false
     */
    public boolean contains(T thing) {
        return bst.contains(thing);
    }


    /**  
     * Removes an element from the set
     * 
     * @param thing the element to be removed
     * @return true if the set was modified, otherwise false
     */
    public boolean remove(T thing) {
        if (contains(thing)){
            bst.remove(thing);
            return true;
        }
        return false;
    }


    /**    
     * Returns the number of elements stored in the set
     * 
     * @return the number of elements stored in the set
     */
    public int length() {
        return bst.length();
    }


    /** 
     * Returns an iterator over the elements stored in the set.
     * 
     * @return an iterator over this set
     */
    public Iterator<T> iterator() {
        return bst.iterator();
    }


    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Set.validate(new TreeSet<Integer>());

        System.out.println("TreeSet passed all tests");
    }
    
}
