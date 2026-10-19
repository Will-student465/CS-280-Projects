package assignments.datastructures;
import java.util.Iterator;
import adt.Tree;


/// A type of Binary tree datastructure backed by a Vector that ensures the tree stays balanced using two conditions
/// 1. a parent node cannot be greater than either of its child nodes
/// 2. the tree is always filled in level order with the smallest values at the top
///
/// @param <T> the type of each element
public class MinHeap<T extends Comparable<T>> extends Vector<T> implements adt.PriorityQueue<T>, adt.Tree<T> {
    
    int size = 0; // keeps track of the number of elements in the vector


    /** 
     * enqueues a value to the end of the Vector and swaps the added element with its parent until post condition is met.
     * 
     * post conditions - No parent element can be greater than either of its children and the tree is filled in level order
     * 
     * @param value the element to be enqueued
     */
    public void enqueue(T value) {
        this.insert(this.length(), value);
        size++;

        int cursor = size - 1;

        while (cursor > 0) {
            int parent = (cursor - 1) / 2;
            if (this.at(cursor).compareTo(this.at(parent)) >= 0) {
                return;
            }

            T temp = this.at(cursor);


            this.set(cursor, this.at(parent));
            this.set(parent, temp);

            cursor = parent;
        }
    }



    /** 
     * Removes the element at index 0 and replaces it with the element at last index in Vector,
     * then moves this element down until the condition is satisfied
     * 
     * post conditions - No parent element can be greater than either of its children and the tree is filled in level order
     * 
     * @return the value that was dequeued
     */
    public T dequeue() {

        if (isEmpty()){
            return null;
        }

        T dequeued = this.at(0);

        if (size == 1) {
            size--;
            this.delete(0);
            return dequeued;
        }

        this.set(0, this.at(size - 1));

        this.delete(size - 1);
        size--;

        int cursor = 0;

        while (true) {

            int left = (2 * cursor) + 1;
            int right = (2 * cursor) + 2;
            int small = cursor;

            if (left < size && this.at(left).compareTo(this.at(small)) < 0) {
                small = left;
            }

            if (right < size && this.at(right).compareTo(this.at(small)) < 0) {
                small = right;
            }

            if (small == cursor) {
                return dequeued;
            }

            T temp = this.at(cursor);
            this.set(cursor, this.at(small));
            this.set(small, temp);
            cursor = small;
    }
}


    /** 
     * Checks if the Vector is empty
     * 
     * @return true or false
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
        if (isEmpty()) {
            return null;
        }
        return this.at(0);
    }


    /// Implements Tree<T> interface methods in this section
    /// 

    /**
     * Compute the degree of the tree, i.e. the largest number of children any single node has.
     * @return the degree of the tree
     */
    public int degree() {
        if (this.length() >= 3) {
            return 2;
        }

        else if (this.length() > 1) {
            return 1;
        }

        else {
            return 0;
        }
    }

    /**
     * Compute the height of the tree, i.e. the longest path to descend from the root to a leaf.
     * @return the height of the tree
     */
    public int height() {
        if (this.length() == 0) {
            return 0;
        }
        int i = 1;
        int height = 1;
        while (i < this.length()) {
            height++;
            i = (i * 2) + 1;
        }
        return height;
    }


    /**
     * Perform an in-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> inorder() {
        Vector<T> vec = new Vector<>();

        LinkedList<Integer> stack = new LinkedList<>();

        int cursor = 0;

        while (!stack.isEmpty() || cursor < this.length()) {
            while (cursor < this.length()) {
                stack.push(cursor);
                cursor = (2 * cursor) + 1;
            }

            cursor = stack.pop();
            vec.push(this.at(cursor));

            cursor = (2 * cursor) + 2;
        }
        return vec.iterator();
    }

    /**
     * Compute the number of items in this tree.
     * @return the number of items
     */
    public int length() {
        return size;
    }

    /**
     * Perform a level order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> levelorder() {
        Vector<T> vec = new Vector<>();

        for (int i = 0; i < this.length(); i++) {
            vec.push(this.at(i));
        }
        return vec.iterator();
    }

    /**
     * Perform a post-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> postorder() {
        Vector<T> vec = new Vector<>();

        if (this.length() == 0) {
            return vec.iterator();
        }

        LinkedList<Integer> firstStack = new LinkedList<>();
        LinkedList<Integer> secondStack = new LinkedList<>();

        firstStack.push(0);

        while (!firstStack.isEmpty()) {
            int cursor = firstStack.pop();

            secondStack.push(cursor);

            int left = (2 * cursor) + 1;
            int right = (2 * cursor) + 2;

            if (left < this.length()) {
                firstStack.push(left);
            }

            if (right < this.length()) {
                firstStack.push(right);
            }
        }

        while (!secondStack.isEmpty()) {
            vec.push(this.at(secondStack.pop()));
        }

        return vec.iterator();
    }

    /**
     * Perform a pre-order traversal of the tree.
     * @return an iterator
     */
    public Iterator<T> preorder() {
        Vector<T> vec = new Vector<>();

        LinkedList<Integer> stack = new LinkedList<>();

        if (this.length() == 0) {
            return vec.iterator();
        }

        int cursor = 0;

        stack.push(cursor);

        while (!stack.isEmpty()) {
            cursor = stack.pop();
            vec.push(this.at(cursor));

            int right = (2 * cursor) + 2;
            int left = (2 * cursor) + 1;

            if (right < this.length()) {
                stack.push(right);
            }

            if (left < this.length()) {
                stack.push(left);
            }
        }
        return vec.iterator();
    }


    /**
     * Checks if MinHeap passes interface validation.
     * @param queue the interface validation tests to use.
     */
    static void validate(MinHeap<Integer> queue) {

    }


    /**
     * Run validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        Tree.validate(new MinHeap<Integer>());

        MinHeap<Integer> heap = new MinHeap<>();

        assert heap.length() == 0;
        assert heap.degree() == 0;
        assert heap.height() == 0;
        assert heap.dequeue() == null;

        // Build sample heap.
        heap.enqueue(12);
        heap.enqueue(3);
        heap.enqueue(8);
        heap.enqueue(16);
        heap.enqueue(0);

        // Check structure.
        assert heap.length() == 5;
        assert heap.degree() == 2;
        assert heap.height() == 3;
        assert heap.peek() == 0;

        // Check traversals.
        assert testTraversal(heap.preorder(),
                new int[]{0, 3, 16, 12, 8});

        assert testTraversal(heap.inorder(),
                new int[]{16, 3, 12, 0, 8});

        assert testTraversal(heap.postorder(),
                new int[]{16, 12, 3, 8, 0});

        assert testTraversal(heap.levelorder(),
                new int[]{0, 3, 8, 16, 12});

        // Check heap property.
        assert heap.dequeue() == 0;
        assert heap.dequeue() == 3;
        assert heap.dequeue() == 8;
        assert heap.dequeue() == 12;
        assert heap.dequeue() == 16;
        assert heap.isEmpty();

        // Duplicate values.
        heap.enqueue(5);
        heap.enqueue(5);
        heap.enqueue(5);

        assert heap.dequeue() == 5;
        assert heap.dequeue() == 5;
        assert heap.dequeue() == 5;



        System.out.println("MinHeap passes all tests");
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
