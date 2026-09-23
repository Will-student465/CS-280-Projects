package assignments.datastructures;

import java.util.Iterator;

import adt.List;

import adt.Queue;


/// @param <T> The type of each element
public class CircularLinkedList<T> implements List<T>, Iterable<T>, Queue<T> {
    private Node tail;
    private int size;


    /**
     * Initialize an empty Circular Linked List
     */
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }



    /**
     * Deletes the element in the head of the Queue
     * 
     * @see adt.Queue#dequeue()
     */
    public T dequeue() {
        return delete(0);
    }

    /**
     *  Add an element to the start of the Queue
     * 
     * @see adt.Queue#enqueue(java.lang.Object)
     */
    public void enqueue(T value) {
        insert(size, value);
    }

    /**
     * Checks if the Queue is empty
     * 
     * @return True if the Queue is empty, otherwise, false
     * @see adt.Queue#isEmpty()
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Reports the next item that can be removed from the Queue
     * 
     * @return The element that can be removed next
     * @see adt.Queue#peek()
     */
    public T peek() {
        return tail.link.data;
    }





    /** 
     * Returns an iterator over each element in a CircularLinkedList using increasing indexes.
     * 
     * @return the iterator over each element in the CircularLinkedList
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node cursor;
            int count = 0;
            {
            if (tail == null) {
                cursor = null;
            }
            else {
                cursor = tail.link;
            }
        }

            public boolean hasNext() {
                return count < size;
            }
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T temp = cursor.data;
                cursor = cursor.link;
                count++;
                return temp;
            }
        };

    }


    /**
     * Compute the number of items in this list.
     * @return the number of items
     */
    public int length() {
        return size; 
    }


    /**
     * Fetch an item from the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        assert 0 <= index && index < this.length();
        Node cursor = tail.link;  // set cursor to point at the first Node
        for (int i = 0; i < index; i++) {
            cursor = cursor.link; // iterate through LinkedList to the index
        }
        return cursor.data; // return the data in Node at index
    }


    /**
     * Change an item in the list.
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;
        Node cursor = tail.link; // set cursor to point at the first Node
        for (int i = 0; i < index; i++) {
            cursor = cursor.link; // iterate through LinkedList to given index
        }
        cursor.data = value; // set value at a given index to a given value
        return; 
    }


    /**
     * Check if the list contains a given value.
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        Node cursor = tail.link; // set cursor to point at the last Node
        for (int i = 0; i < this.length(); i++) {
            if (cursor.data.equals(value)) { // check list iteratively and return true if a node matches given value
                return true; 
            }
            cursor = cursor.link; // iterate through LinkedList until value is found end of List is reached
        }
        return false; // returns false if value is not found
    }


    /**
     * Insert an item into the list.
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size; 
        if (this.length() == 0) {
            Node newNode = new Node(value, null);
            tail = newNode;
            tail.link = tail;
            size++;
            return;
        }
        Node cursor = tail.link;
        if (index == 0) { // special case to insert at head
            Node temp = this.tail.link; // insert at index 0 for new node to become head
            Node newNode = new Node(value, temp); // set new node to point at old head
            this.tail.link = newNode; // update head and then size
            size++;
            return;
        }
        if (index == this.length()) {
            Node head = tail.link;
            Node newNode = new Node(value, head);
            tail.link = newNode;
            tail = newNode;
            size++;
            return;
        }
        for (int i = 0; i < index - 1; i ++) {
            cursor = cursor.link; // iterate to node before insertion index
        }
        Node temp = cursor.link; 
        Node newNode = new Node(value, temp); // newNode points to temp
        cursor.link = newNode; // link previous node to newNode to insert
        this.size++;
    }


    /**
     * Remove an item from the list.
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.length();
        if (1 == this.length()) { // special case if list only has one element
            Node delete = tail;
            tail = null;
            size--;
            return delete.data;
        }
        Node cursor = tail.link;
        if (index == 0) { // special case to delete head
            Node delete = tail.link;
            tail.link = delete.link;
            size--;
            return delete.data;
        }
        if (index == this.length() - 1) { // special case to delete tail
            for (int i = 0; i < index - 1; i++) {
                cursor = cursor.link;
            }
            Node delete = cursor.link;
            cursor.link = delete.link;
            tail = cursor;
            size--;
            return delete.data;
        }
        for (int i = 0; i < index - 1; i ++) {
            cursor = cursor.link; // iterate through LinkedList to index before node to delete
        }
        Node delete = cursor.link; 
        cursor.link = delete.link; // deletes the node at a given index
        size--;
        return delete.data;
    }
    


    /**
     * A value with a pointer, allowing us to link to another value
     */
    private class Node {
        T data;
        Node link;

        /**
         * Initialize a node with no children
         * @param data the data value
         * @param link the next node in the chain
         */
        Node(T data, Node link) {
            this.data = data;
            this.link = link;
        }

    }


    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        List.validate(new CircularLinkedList<>());
        Queue.validate(new CircularLinkedList<>());

        // Test iterator.
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        for (int i = 0; i < 5; i ++) list.insert(0, i);
        Iterator<Integer> iter = list.iterator();
        for (int i = 5; i > 0; i --) assert iter.next().equals(i-1);
        assert !iter.hasNext();

        System.out.println("CircularLinkedList passes all tests.");
    }

}
