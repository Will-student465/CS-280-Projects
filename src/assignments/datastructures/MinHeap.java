package assignments.datastructures;


/// A type of Binary tree datastructure backed by a Vector that ensures the tree stays balanced using two conditions
/// 1. a parent node cannot be greater than either of its child nodes
/// 2. the tree is always filled in level order with the smallest values at the top
///
/// @param <T> the type of each element
public class MinHeap<T extends Comparable<T>> extends Vector<T> implements adt.PriorityQueue<T>{
    
    int size = 0; // keeps track of the last element in the vector to make methods easier


    /** 
     * enqueues a value to the end of the Vector and calls "enqueueHelper" to ensure post condition is met.
     * 
     * post conditions - No parent element can be greater than either of its children && the tree is filled in level order
     * 
     * @param value the element to be enqueued
     */
    public void enqueue(T value) {
        this.insert(this.length(), value);
        size++;
        enqueueHelper(size - 1);
    }

    /**
     * checks if the element at given index meets post condition, and swaps it with its parent element if it does not. then recursively repeats this operation until post condition is met for the whole tree.
     * 
     * post conditions - No parent element can be greater than either of its children && the tree is filled in level order
     * 
     * @param index the index of the elemnt that may be swapped with its parent
     */
    private void enqueueHelper(int index) {
        int parent = (index - 1) / 2;
        if (this.at(index).compareTo(this.at(parent)) < 0) {
            T temp = this.at(index);
            this.set(index, this.at(parent));
            this.set(parent, temp);
            enqueueHelper(parent);
        }
        return;
    }

    /** 
     * Removes the element at index 0 and replaces it with the element at last index in Vector,
     * then calls dequeueHelper to enforce post condition.
     * 
     * post conditions - No parent element can be greater than either of its children && the tree is filled in level order
     * 
     * @return the value that was dequeued
     */
    public T dequeue() {
        T returnVal = this.at(0);
        this.set(0, this.at(size));
        this.delete(size);
        dequeueHelper(0);
        size--;
        return returnVal;
    }

    /**
     * checks if the element at a given index meets post condition, 
     * then swaps it with the smaller of its children if it does not meet the post condition and recursively repeats this operation until post condition is met.
     * 
     * @param index the index of the element that may be swapped with one of its children
     */
    private void dequeueHelper(int index) {
        int left = (index * 2) + 1;
        int right = (index + 1) * 2;
        T temp = this.at(index);

        int compareLeft = this.at(index).compareTo(this.at(left));
        int compareRight = this.at(index).compareTo(this.at(right));
        int compareChildren = this.at(left).compareTo(this.at(right));


        if (compareLeft > 0 && compareRight > 0) {
            if (compareChildren < 0) {
                this.set(index, this.at(left));
                this.set(left, temp);
                dequeueHelper(left);
            }
            else {
                this.set(index, this.at(right));
                this.set(right, temp);
                dequeueHelper(right);
            }
        }
        else if (compareLeft > 0) {
            this.set(index, this.at(left));
            this.set(left, temp);
            dequeueHelper(left);
        }
        else if (compareRight > 0) {
            this.set(index, this.at(right));
            this.set(right, temp);
            dequeueHelper(right);
        }
        else return;
    }

    /** 
     * Checks if the Vector is empty
     * 
     * @return true or flase
     */
    public boolean isEmpty() {
        return this.length() == 0;
    }

    /** 
     * peeks at the next element to be dequeued
     * 
     * @return the next element to be removed
     */
    public T peek() {
        return this.at(0);
    }


    /**
     * Checks if MinHeap passes interface validation.
     * @param queue the interface validation tests to use.
     */
    static void validate(MinHeap<Integer> queue) {

    }

}
