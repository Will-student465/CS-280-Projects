package assignments.datastructures;

import java.util.Iterator;

import assignments.datastructures.Vector;

import assignments.datastructures.LinkedList;

import assignments.datastructures.CircularLinkedList;

import adt.Queue;
import adt.Tree;

/// A data structure where data is organized into a mathematical tree structure,
///  comprised of nodes which have at most two children.
/// 
/// The binary search tree satisfies the binary search tree condition:
///  every node has a value greater than that of its less child,
///  and less than or equal to that of its right child.
/// 
/// @param <T> the type of each element, which must have a natural ordering
public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {
    private Node root;

    /**
     * Initialize an empty tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Manually construct a tree so we can test iterator methods.
     * 
     * NOTE: You will remove this method once we've learned how to add.
     * 
     * @param demo a specific sequence of numbers defined in the main method
     */
    public void fillForDay1Tests(T[] demo) {
        this.root = new Node(demo[0]);
        this.root.left = new Node(demo[1]);
        this.root.left.left = new Node(demo[2]);
        this.root.left.right = new Node(demo[3]);
        this.root.left.left.right = new Node(demo[4]);
        this.root.left.right.left = new Node(demo[5]);
        this.root.left.left.right.right = new Node(demo[6]);
        this.root.right = new Node(demo[7]);
        this.root.left.left.right.right.left = new Node(demo[8]);
        this.root.left.right.right = new Node(demo[9]);
        this.root.left.right.left.right = new Node(demo[10]);
        this.root.left.right.left.left = new Node(demo[11]);
        this.root.left.right.right.right = new Node(demo[12]);
        this.root.left.right.left.right.right = new Node(demo[13]);
        this.root.left.left.right.right.right = new Node(demo[14]);
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        //TODO (Hint: use recursion.)
        return lengthR(this.root);
    }

    private int lengthR(Node cursor) {
        if (cursor == null) {
            return 0;
        }

        int left = lengthR(cursor.left);
        int right = lengthR(cursor.right);

        return left + right + 1;
    }


    private void traversal(Node root, Vector<T> data){
        data.push(root.data);
    }
    
    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        // TODO (Hint: use recursion.)
        return degreeHelper(this.root);
    }

    private int degreeHelper(Node cursor) {
        if (cursor == null) {
            return 0;
        }

        int links = 0;

        if (cursor.left != null) {
            links++;
        }
        if (cursor.right != null) {
            links++;
        }

        int left = degreeHelper(cursor.left);
        int right = degreeHelper(cursor.right);

        int max = links;
        if (left > max) {
            max = left;
        }
        if (right > max) {
            max = right;
        }

        return max;
    }
    
    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */
    public int height() {
        // TODO (Hint: use recursion.)
        return heightHelper(this.root);
    }


    private int heightHelper(Node cursor) {
        if (cursor == null) {
            return 0;
        }

        int left = heightHelper(cursor.left);
        int right = heightHelper(cursor.right);


        int max = left;
        if (right > max) {
            max = right;
        }

        return max + 1; // to get the root
    }



    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        // TODO (Hint: use recursion.)
        Vector<T> vec = new Vector<>();
        Node cursor = this.root;
        preorderMove(cursor, vec);

        return vec.iterator();
        
    }

    private void preorderMove(Node cursor, Vector<T> vec) {
        if (cursor == null){
            return;
        }

        traversal(cursor, vec);

        preorderMove(cursor.left, vec);

        preorderMove(cursor.right, vec);
    }
    
    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        // TODO (Hint: use recursion.)
        Vector<T> vec = new Vector<>();
        Node cursor = this.root;
        inorderMove(cursor, vec);

        return vec.iterator();
        
    }

    private void inorderMove(Node cursor,Vector<T> vec){
        if (cursor == null){
            return;
        }
        
        inorderMove(cursor.left, vec);
        
        traversal(cursor, vec);
        
        inorderMove(cursor.right, vec);
        
    }
    
    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        // TODO (Hint: use recursion.)
        Vector<T> vec = new Vector<>();
        Node cursor = this.root;
        postorderMove(cursor, vec);

        return vec.iterator();
    }

    private void postorderMove(Node cursor, Vector<T> vec){
        if (cursor == null){
            return;
        }

        postorderMove(cursor.left, vec);

        postorderMove(cursor.right, vec);

        traversal(cursor, vec);
    }
    
    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        // TODO (Hint: don't use recursion.)
        Vector<T> vec = new Vector<>();
        
        if (this.root == null){
            return vec.iterator();
        }


        CircularLinkedList<BinarySearchTree<T>.Node> que = new CircularLinkedList<>();
        que.enqueue(this.root);


        while(!que.isEmpty()) {
            Node cursor = que.dequeue();
            vec.push(cursor.data);
        

            if (cursor.left != null) {
                que.enqueue(cursor.left);
            }


            if (cursor.right != null) {
                que.enqueue(cursor.right);
            }


        }
        return vec.iterator();
    }


    
    /**
     * Iterate over all elements in-order.
     * @return an iterator
     */
    public Iterator<T> iterator() {
        return this.inorder();
    }



    /**
     * Add an element to a Binary Search tree 
     * use recursion to find where the element belongs in the tree.
     * 
     * @param value the element to be added
     * @return True after the value is added
     */
    public boolean add(T value) {
        if (this.root == null) this.root = new Node(value);
        

        return addHelper(this.root, value);
    }

    /**
     * Compares left and right to decide which branch the element to be added belongs on.
     * 
     * 
     * @param cursor the function's current location in the tree
     * @param value the value to be added
     * @return True once the value is added
     */
    private boolean addHelper(Node cursor, T value) {
        
        if (cursor.data.compareTo(value) <= 0) {
            if (cursor.right == null) {
                cursor.right = new Node(value);
                return true;
            }
            return addHelper(cursor.right, value);
        }
        else {
            if (cursor.left == null){
                cursor.left = new Node(value);
                return true;
            }
            return addHelper(cursor.left, value);
        }
    }
        
    
    /**
     * removes the given element from the tree using recursion to find the element.
     * 
     * @param value the value to be removed
     * @return true if the value exists and is removed, otherwise false
     */
    public boolean remove(T value) {
        if (!contains(value)) {
            return false;
        }
        this.root = removeHelper(this.root, value);
        return true;
    }

    /**
     * uses recursion to navigate and find the first element of that value
     * then removes it and moves any data below it into place
     * 
     * @param cursor the function's current position in the tree
     * @param value the element to be added
     * @return true if the value exists and is removed, otherwise false
     */
    private Node removeHelper(Node cursor, T value) {
        if (cursor == null) return null;

        if (value.compareTo(cursor.data) < 0) {
            cursor.left = removeHelper(cursor.left, value);
        }
        else if (value.compareTo(cursor.data) > 0) {
            cursor.right = removeHelper(cursor.right, value);
        }
        else {
            if (cursor.left == null && cursor.right == null) {
                return null;
            }

            if (cursor.left == null) return cursor.right;
            
            if (cursor.right == null) return cursor.left;

            Node next = cursor.right;
            while (next.left != null) {
                next = next.left;
            }

            cursor.data = next.data;
            cursor.right = removeHelper(cursor.right, next.data);
        }
        
        return cursor;
    }

    /**
     * checks for a given element in a Binary Search Tree
     * 
     * @param value the value to be searched for
     * @return true if the value is in the tree, otherwise false
     */
    public boolean contains(T value) {
        Iterator<T> cursor = this.levelorder();

        while (cursor.hasNext()) {
            if (cursor.next().compareTo(value) == 0) return true;
        }
        return false;
    }

    // NOTE: You're going to add more public methods here on the second day of trees.
    
    /**
     * An encapsulation of a value with two pointers, suitable for a binary tree.
     */
    private class Node {
        T data;
        Node left;      // Left child.
        Node right;     // Right child.

        /**
         * Initialize a node with no children.
         * @param data the data value
         */
        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new BinarySearchTree<>());

        // Build a sample tree.
        Integer[] numbers = {4, -4, -6, 0, -6, -3, -5, 5, -6, 0, -3, -4, 3, -3, -5};
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.fillForDay1Tests(numbers);     // NOTE: To be replaced once we learn how to add.

        // Check structure.
        assert tree.length() == 15;
        assert tree.degree() == 2;
        assert tree.height() == 6;

        // Check traversals.
        assert testTraversal(tree.preorder(),   new int[]{4, -4, -6, -6, -5, -6, -5, 0, -3, -4, -3, -3, 0, 3, 5});
        assert testTraversal(tree.inorder(),    new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});
        assert testTraversal(tree.postorder(),  new int[]{-6, -5, -5, -6, -6, -4, -3, -3, -3, 3, 0, 0, -4, 5, 4});
        assert testTraversal(tree.levelorder(), new int[]{4, -4, 5, -6, 0, -6, -3, 0, -5, -4, -3, 3, -6, -5, -3});
        assert testTraversal(tree.iterator(),   new int[]{-6, -6, -6, -5, -5, -4, -4, -3, -3, -3, 0, 0, 3, 4, 5});


        // Test tree modifier methods.
        assert !tree.contains(15);
        assert tree.contains(-5);
        assert tree.contains(3);
        assert !tree.contains(12);

        tree.add(15);
        assert tree.contains(15);
        tree.add(7777);
        assert tree.contains(7777);

        tree.remove(15);
        assert !tree.contains(15);
        tree.remove(7777);
        assert !tree.contains(7777);

        System.out.println("BinarySearchTree passes all tests.");
    }

    /**
     * Convenience method to test tree traversals.
     * @param iterator newly-constructed iterator
     * @param array expected values
     */
    private static boolean testTraversal(Iterator<Integer> iterator, int[] array) {
        boolean expected = true;
        for (int i = 0; i < array.length; i ++) {
            Integer next = iterator.next();
            expected = expected && next.equals(array[i]);
        }
        return expected && !iterator.hasNext();
    }
}