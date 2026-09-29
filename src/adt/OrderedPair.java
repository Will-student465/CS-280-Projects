package adt;

public interface OrderedPair<K, V> {
    K first();
    V second();
    OrderedPair<V, K> reversed();

    static <K, V> void validate(OrderedPair<K, V> pair) {}
}
