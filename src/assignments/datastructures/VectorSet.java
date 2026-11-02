package assignments.datastructures;

import java.util.Iterator;

import adt.Set;


public class VectorSet<T extends Comparable<T>> implements Set<T> {

    Vector<T> vector = new Vector<>();

    public boolean add(T thing) {

        if (contains(thing)) {
            return false;
        }

        vector.push(thing);

        return true;
    }

    public boolean contains(T thing) {

        return vector.contains(thing);
    }

    public boolean remove(T thing) {
        if (contains(thing)) {
            vector.delete(vector.find(thing));
            return true;
        }
        return false;
    }

    public int length() {
        return vector.length();
    }

    public Iterator<T> iterator() {
        return vector.iterator();
    }


    //public static void validate(Set<> Set) {

    //}

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Set.validate(new VectorSet<Integer>());

        System.out.println("VectorSet passed all tests");
    }
}