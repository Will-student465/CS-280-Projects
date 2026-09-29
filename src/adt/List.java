package adt;

public interface List<T> {
    void insert(int index, T value);
    int length();
    T at(int index);
    static <T> void validate(List<T> list) {}

}
