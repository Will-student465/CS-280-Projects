package assignments.datastructures;

import adt.List;

/// An extensible list backed by a chain of nodes.
/// 
/// The idea here is to wrap each datum in a larger structure, a *node*,
///  which also contains a pointer to the node containing the *next* element in the list.
/// This structure permits efficient insertion and deletion,
///  in the sense that it only requires rearranging pointers nearby where the change takes place.
/// 
/// However, this structure foregoes *random access*, i.e. easy access to arbitrary locations in the list.
/// In order to make any changes to a location in the middle of the list,
///  one must first traverse through the chain of nodes from the beginning of the list.
/// 
/// @param <T> the type of each element
public class LinkedList<T> implements List<T> {
    private Node head;
    private int size;

    /**
     * Initialize an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Compute the number of items in this list.
     * @return the number of items
     */
    public int length() {
        // TODO implement this method
        return size; 
    }
    
    /**
     * Fetch an item from the list.
     * @param cursor the object used to iterate through Nodes in a Linked List
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @return the value stored at the given location
     */
    public T at(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method
        Node cursor = this.head;  // set cursor to point at the first Node
        for (int i = 0; i < index; i++) {
            cursor = cursor.link; // iterate through LinkedList to the index
        }
        return cursor.data; // return the data in Node at index
        }
    
    
    /**
     * Change an item in the list.
     * @param cursor the object used to iterate through Nodes in a Linked List
     * @param index the location of the item - a nonnegative integer less than the length of the list
     * @param value the new value to assign at the given location
     */
    public void set(int index, T value) {
        assert 0 <= index && index < this.size;
        // TODO implement this method
        Node cursor = this.head; // set cursor to point at the first Node
        for (int i = 0; i < index; i++) {
            cursor = cursor.link; // iterate through LinkedList to given index
        }
        cursor.data = value; // set value at a given index to a given value
        return; 

        

    }
    
    /**
     * Check if the list contains a given value.
     * @param cursor the object used to iterate through Nodes in a Linked List
     * @param value the value to look for
     * @return true iff the collection contains value
     */
    public boolean contains(T value) {
        // TODO implement this method
        Node cursor = this.head; // set cursor to point at the first Node
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
     * @param temp the variable used to store value
     * @param newNode the variable used to insert a new node
     * @param cursor the object used to iterate through Nodes in a Linked List
     * @param index the location of where to put the item - a nonnegative integer less than or equal to the length of the list
     * @param value the new value to put at the given location
     */
    public void insert(int index, T value) {
        assert 0 <= index && index <= this.size;
        // TODO implement this method
        Node cursor = this.head; 
        if (index == 0) { // special case to insert at head
            Node temp = this.head; // insert at index 0 for new node to become head
            Node newNode = new Node(value, temp); // set new node to point at old head
            this.head = newNode; // update head and then size
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
     * @param delete the variable used to store and delete data at a given index
     * @param cursor the object used to iterate through Nodes in a linked list
     * @param index the location to delete from - a nonnegative integer less than the length of the list
     * @return the value which was removed
     */
    public T delete(int index) {
        assert 0 <= index && index < this.size;
        // TODO implement this method
        Node cursor = this.head;
        if (index == 0) { // special case to delete head
            Node delete = this.head;
            this.head = delete.link;
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
     * An encapsulation of a value with a pointer, allowing us to chain to another value.
     */
    private class Node {
        T data;
        Node link;

        /**
         * Initialize a node with no children.
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
        List.validate(new LinkedList<>());
        System.out.println("LinkedList passes all tests.");
    }

}

