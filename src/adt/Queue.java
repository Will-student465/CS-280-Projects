package adt;

public interface Queue<T> {
    void enqueue(T value);
    T dequeue();
    static <T> void validate(Queue<T> queue) {}
}
