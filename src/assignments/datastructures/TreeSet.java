package assignments.datastructures;

import assignments.datastructures.BinarySearchTree;

import adt.Set;

import java.util.Iterator;

public class TreeSet<T extends Comparable<T>> implements Set<T> {

    BinarySearchTree<T> bst = new BinarySearchTree<>();

    public boolean add(T thing) {

        if (contains(thing)) return false;

        bst.add(thing);
        
        return true;
    }


    public boolean contains(T thing) {
        return bst.contains(thing);
    }

    public boolean remove(T thing) {
        if (bst.contains(thing)){
            bst.remove(thing);
            return true;
        }
        return false;
    }


    public int length() {
        return bst.length();
    }

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
