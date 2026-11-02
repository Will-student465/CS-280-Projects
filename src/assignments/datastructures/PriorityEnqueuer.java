package assignments.datastructures;

import adt.PriorityQueue;


/// A type of Queue that uses O(N) runtime for DeQueuing in favor of O(1) EnQueuing to the end of a Vector.
///
/// @param <T> the type of each element
public class PriorityEnqueuer<T extends Comparable<T>> extends Vector<T> implements adt.PriorityQueue<T> {

    //
    // Methods for PriorityEnqueue
    //

    /**
     *  adds an element to the end of the Vector.
     * 
     * @param value the element to be added
     */
    public void enqueue (T value) {
        insert(this.length(), value);
    }

    /**
     * Removes an element from inside of the Vector
     * 
     * @return The element removed
     */
    public T dequeue () {
        int s = 0;
        for (int i = 1; i < this.length(); i ++) {
            if (this.at(i).compareTo(this.at(s)) < 0){
                s = i;
            }
        }
        return delete(s);
    }

    /**
     * Checks if the vector is empty
     * 
     * @return True if the vector is empty, otherwise false
     */
    public boolean isEmpty(){
        return this.length() == 0;
    }


    /**
     *  Peeks at the next element to be DeQueued from the Vector
     * 
     * @return the value of the element to be DeQueued next
     */
    public T peek() {
        int s = 0;
        for (int i = 1; i < this.length(); i ++) {
            if (this.at(i).compareTo(this.at(s)) < 0){
                s = i;
            }
        }
        return this.at(s);
    }

    
    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        PriorityQueue.validate(new PriorityEnqueuer<Integer>());

        System.out.println("PriorityEnqueuer passed all tests.");
    }
}