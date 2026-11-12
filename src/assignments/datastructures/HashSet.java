package assignments.datastructures;

import adt.Set;

import java.util.Iterator;

/// An mathematical set backed by an array of VectorSets.
/// 
/// Uses hashcodes to find the index to store new elements in 
/// while also maintaining set condition of having no duplicate 
/// elements.
/// 
/// No single index can contain more than 5 elements.
/// 
/// @param <T> the type of each element
public class HashSet<T> implements Set<T> {
    
    private VectorSet<T>[] array;
    private int size;

    /**
     * Initialize an empty HashSet
     */
    @SuppressWarnings("unchecked")
    public HashSet() {
        array = (VectorSet<T>[]) new VectorSet[10];

        for (int i = 0; i < array.length; i++) {
            array[i] = new VectorSet<>();
        }

        size = 0;
    }

    /**
     * finds the hash(index) for a value
     * 
     * @param value the value to be hashed
     * @return the index of the bucket the value belongs in
     */
    private int hash(T value) {
        return Math.abs(value.hashCode()) % array.length;
    }

    /**
     * adds an element to the HashSet
     * 
     * reallocates if a sixth item is being added to the same index to maintain HashSet condition.
     * 
     * @param value the element to be added.
     * @return True if a value is added, otherwise false
     */
    public boolean add(T value) {
        if (array[hash(value)].contains(value)) {
            return false;
        }

        array[hash(value)].add(value);
        size++;

        if (array[hash(value)].length() > 5) {

            reallocate();
        }

        return true;
    }


    /**
     * Checks if the HashSet contains a value
     * 
     * @param value the value to be searched for
     * @return true if the value is found, otherwise false
     */
    public boolean contains(T value) {
        return array[hash(value)].contains(value);
    }


    /** 
     * returns the number of elements in the HashSet
     * 
     * @return the number of elements
     */
    public int length() {
        return size;
    }


    /** 
     * Removes an element from the HashSet
     * 
     * @param value the value to be removed
     * @return true if the HashSet is modified, otherwise false
     */
    public boolean remove(T value) {

        if (!array[hash(value)].contains(value)) {
            return false;
        }

        array[hash(value)].remove(value);
        size--;

        return true;
    }

    
    /**
     * resizes the HashSet to allow for more elements to be stored
     * 
     * A new aray of buckets is created and every element is 
     * reinserted using the add method.
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {
        
        VectorSet<T>[] old = array;

        array = (VectorSet<T>[]) new VectorSet[old.length * 2 + 1];

        for (int i = 0; i < array.length; i++) {
            array[i] = new VectorSet<>();
        }

        size = 0;

        for (int i = 0; i < old.length; i++) {

            Iterator<T> iterator = old[i].iterator();

            while (iterator.hasNext()) {
                add(iterator.next());
            }
        }

    }


    /** 
     * iterates over all elements in the HashSet
     * 
     * @return an iterator over each element in the HashSet
     */
    public Iterator<T> iterator() {
        Vector<T> list = new Vector<>();

        for (int i = 0; i < array.length; i++) {

            Iterator<T> iterator = array[i].iterator();

            while (iterator.hasNext()) {
                list.push(iterator.next());
            }
        }
        return list.iterator();
    }


    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Set.validate(new HashSet<Integer>());

        HashSet<Integer> test = new HashSet<>();

        test.add(10);
        test.add(20);
        test.add(30);
        test.add(40);
        test.add(50);
        test.add(60); // this one should force a reallocate call

        assert test.length() == 6;

        assert test.contains(10);
        assert test.contains(20);
        assert test.contains(30);
        assert test.contains(40);
        assert test.contains(50);
        assert test.contains(60);

        assert test.length() == 6;


        System.out.println("HashSet passed all tests");
    }
}
