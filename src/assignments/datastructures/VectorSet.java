package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
 * A mathematical set to store elements backed by a Vector.
 * No duplicate elements may be stored.
 * 
 * @param <T> the type of each element 
 */
public class VectorSet<T> implements Set<T> {

    // Initialize an empty Vector
    Vector<T> vector = new Vector<>();


    /** 
     * Adds an element to the set if the set does not already contain it.
     * 
     * @param thing the element to be added
     * @return true if the element was added, otherwise false
     */
    public boolean add(T thing) {

        if (contains(thing)) {
            return false;
        }

        vector.push(thing);

        return true;
    }


    /**
     * Detects whether the set contains an element.
     * 
     * @param thing the element to be searched for
     * @return true if the element is in the set, otherwise false
     */
    public boolean contains(T thing) {

        return vector.contains(thing);
    }


    /**
     * Removes an element from the set.
     * 
     * @param thing the element to be removed
     * @return true if the set was modified, otherwise false
     */
    public boolean remove(T thing) {
        if (contains(thing)) {
            vector.delete(vector.find(thing));
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
        return vector.length();
    }


    /** 
     * Returns an iterator over the elements stored in the set.
     * 
     * @return an iterator over this set
     */
    public Iterator<T> iterator() {
        return vector.iterator();
    }



    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Set.validate(new VectorSet<Integer>());

        System.out.println("VectorSet passed all tests");
    }
}