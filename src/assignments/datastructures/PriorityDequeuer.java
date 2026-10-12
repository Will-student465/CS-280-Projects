package assignments.datastructures;


/// A type of stack that uses O(N) time to EnQueue each element to the queue in a sorted manner
/// in favor of O(1) DeQueuing runtime.
///
/// @param <T> the type of each element
public class PriorityDequeuer<T extends Comparable<T>> extends Vector<T> implements adt.PriorityQueue<T> {


    /** 
     * Removes the element at the end of the Vector
     * 
     * @return the element removed
     */
    public T dequeue() {
        return pop();
    }


    /**
     *  adds an element into the list by comparing it to indexes in the list to find where it belongs
     * 
     * @param value the element to be added
     */
    public void enqueue(T value) {
        if (isEmpty()){
            insert(0, value);
            return;
        }
        int l = 0;

        while (l < length() && this.at(l).compareTo(value) <= 0) {
            l ++;
        }
        insert(l, value);
    }


    /** 
     * Checks to see if the Vector is empty
     * 
     * @return true if the vector is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.length() == 0;
    }


    /** 
     * Peeks at the next element to be DeQueued.
     * 
     * @return the next element to be DeQueued
     */
    public T peek() {
        return this.at(this.length() - 1);
    }



    /**
     * Checks if PriorityDequeuer passes interface validation.
     * @param queue the interface validation tests to use.
     */
    static void validate(PriorityDequeuer<Integer> queue){

    }
    
}
