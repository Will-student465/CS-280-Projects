package assignments.datastructures;

import java.security.Key;
import java.util.Iterator;
import adt.OrderedPair;

import adt.Map;

/**
 * Implements a Map of VectorSets filled with Key-Value-Pairs backed by an array.
 * The key of each keyvalue pair is hashed to find the array index to store it.
 * 
 * 
 * @param <K> The type of each Key element
 * @param <V> The type of each Value element
 */
public class HashMap<K, V> implements adt.Map<K, V> {

    private VectorSet<KeyValuePair<K, V>>[] array;
    private int size;


    /**
     * Initialize an empty HashMap
     */
    @SuppressWarnings("unchecked")
    public HashMap() {
        array = (VectorSet<KeyValuePair<K, V>>[]) new VectorSet[10];

        for (int i = 0; i < array.length; i++) {
            array[i] = new VectorSet<KeyValuePair<K, V>>();
        }

        size = 0;
    }


    /**
     * finds the hash(index) for a value.
     * 
     * @param value the value to be hashed
     * @return the index of the bucket the value belongs in
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % array.length;
    }
    
    /**
     * Checks if a given key exists in the HashMap.
     * 
     * @param key the key to be found
     * @return true if the key exists, otherwise false
     */
    public boolean contains(K key) {
        for (KeyValuePair<K, V> i: array[hash(key)]) {
            if (i.first().equals(key)) {
                return true;
            }
        }
        return false;
    }


    /** 
     * Finds the value attached to a given key in the HashMap.
     * 
     * @param key the key to be found
     * @return the value attached to the key, otherwise null
     */
    public V get(K key) {
        for (KeyValuePair<K, V> i: array[hash(key)]) {
            if (i.first().equals(key)) {
                return i.second();
            }
        }
        return null;   
    }


    /**
     * Returns the number of elements in the HashMap.
     * 
     * @return number of elements in the HashMap
     */
    public int length() {
        return size;
    }


    /**
     * Adds a new Key Value pair into the HashMap.
     * 
     * @param Key the key of the pair to be added
     * @param value the value of the pair to be added
     * @return the previous value if the given key already had a value, otherwise null
     */
    public V put(K key, V value) {
        for (KeyValuePair<K, V> i: array[hash(key)]) {
            if (i.first().equals(key)) {
                V temp = i.second();
                i.setValue(value);
                return temp;
            }
        }
        array[hash(key)].add(new KeyValuePair<>(key, value));
        size++;

        if (array[hash(key)].length() > 5) {
            reallocate();
        }

        return null;
    }


    /**  
     * Removes a Key Value pair from the HashMap
     * 
     * @param key the key of the Key Value pair to be removed
     * @return the value of the pair removed
     */
    public V remove(K key) {
        VectorSet<KeyValuePair<K, V>> b = array[hash(key)];

        KeyValuePair<K, V> i = null;

        for (KeyValuePair<K, V> pair : b) {
            if (pair.first().equals(key)) {
                i = pair;
                break;
            }
        }

        if (i == null) {
            return null;
        }

        V temp = i.second();

        b.remove(i);

        size--;

        return temp;
    }


    /**
     * Copies all elements from the original HashMap into a new HashMap with more allocated space.
     */
    @SuppressWarnings("unchecked")
    public void reallocate() {

        VectorSet<KeyValuePair<K, V>>[] old = array;

        array = (VectorSet<KeyValuePair<K, V>>[]) new VectorSet[old.length * 2 + 1];

        for (int i = 0; i < array.length; i++) {
            array[i] = new VectorSet<>();
        }

        size = 0;

        for (int i = 0; i < old.length; i++) {

            Iterator<KeyValuePair<K, V>> iterator = old[i].iterator();

            while (iterator.hasNext()) {
                KeyValuePair<K, V> temp = iterator.next();
                put(temp.first(), temp.second());
            }
        }
    }


    /** 
     * Returns an iterator over each Key Value pair in the HashMap.
     * 
     * @return an iterator over each Key Value pair in the HashMap
     */
    public Iterator<OrderedPair<K, V>> iterator() {
        Vector<OrderedPair<K, V>> list = new Vector<>();

        for (int i = 0; i < array.length; i++) {
            for (KeyValuePair<K, V> p : array[i]) {
                list.push(p);
            }
        }
        return list.iterator();
    }


    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        HashMap<Integer, String> test = new HashMap<>();


        test.put(10, "ten");
        test.put(20, "twenty");
        test.put(30, "thirty");
        test.put(40, "forty");
        test.put(50, "fifty");
        test.put(60, "sixty"); // This value causes a reallocation


        // begin testing that all Key Value pairs are preserved
        assert test.get(10).equals("ten");
        assert test.get(20).equals("twenty");
        assert test.get(30).equals("thirty");
        assert test.get(40).equals("forty");
        assert test.get(50).equals("fifty");
        assert test.get(60).equals("sixty");

        assert test.contains(10);
        assert test.contains(20);
        assert test.contains(30);
        assert test.contains(40);
        assert test.contains(50);
        assert test.contains(60);

        assert test.length() == 6;

        System.out.println("HashMap passed all tests");
    }


}
