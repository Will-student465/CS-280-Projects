package adt;

public interface Stack<T> {
    void push(T value);
    T pop();
    static <T> void validate(Stack<T> stack) {}
}
