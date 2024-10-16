package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        public ArrayDequeIterator() {
             index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            int pos = (index + first) % capacity;
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
    private int capacity = 0;
    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            a[i] = get(i);
        }
        items = a;
        first = 0;
        last = size - 1;
        nextFirst = capacity - 1;
        nextLast = size;
        this.capacity = capacity;
    }

    @Override
    public void addFirst(T item) {
        if (size == 0) {
            last = 0;
        }
        if (size == capacity) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        first = nextFirst;
        size += 1;
        nextFirst = ((nextFirst - 1) % capacity + capacity) % capacity;
    }

    @Override
    public void addLast(T item) {
        if (size == 0) {
            first = 1;
        }
        if (size == capacity) {
            resize(size * 2);
        }
        items[nextLast] = item;
        last = nextLast;
        size += 1;
        nextLast = (nextLast + 1) % capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((size < (capacity / 4) + 1) && (size >= 16)) {
            resize(capacity / 4);
        }
        T returnItem = items[first];
        items[first] = null;
        nextFirst = first;
        first = (first + 1) % capacity;
        size -= 1;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((size < (capacity / 4) + 1) && (size >= 16)) {
            resize(capacity / 4);
        }
        T returnItem = items[last];
        items[last] = null;
        nextLast = last;
        last = ((last - 1) % capacity + capacity) % capacity;
        size -= 1;
        return returnItem;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(i + first) % capacity] + " ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int pos = (first + index) % capacity;
        return items[pos];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
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
