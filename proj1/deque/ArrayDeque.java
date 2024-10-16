package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        public ArrayDequeIterator() {
             index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            int pos = (index + first) % size;
            T returnItem = items[pos];
            index += 1;
            return returnItem;
        }
    }

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int first;
    private int last;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 8;
        nextFirst = 0;
        nextLast = 1;
    }

    public void addFirst(T item) {
        if (size == 0) {
            last = 0;
        }
        items[nextFirst] = item;
        first = nextFirst;
        size += 1;
        nextFirst = ((nextFirst - 1) % size + size) % size;
    }

    public void addLast(T item) {
        if (size == 0) {
            first = 1;
        }
        items[nextLast] = item;
        last = nextLast;
        size += 1;
        nextLast = (nextLast + 1) % size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnItem = items[first];
        items[first] = null;
        size -= 1;
        first = (first + 1) % size;
        return returnItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnItem = items[last];
        items[last] = null;
        size -= 1;
        last = ((last - 1) % size + size) % size;
        return returnItem;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(i + first) % size] + " ");
        }
        System.out.println();
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int pos = (first + index) % size;
        return items[pos];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o instanceof ArrayDeque) {
            ArrayDeque ad = (ArrayDeque) o;
            if (size != ad.size()) { return false; }
            for (int i = 0; i < size; i++) {
                if (get(i) != ad.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}
