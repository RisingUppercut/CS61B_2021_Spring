package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        public ArrayDequeIterator() {
             pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private static final int INITIAL_CAPACITY = 8;
    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int arrayIndex(int i) {
        return (nextFirst + 1 + i) % items.length;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int old_capacity = items.length;
        for (int i = 0; i < size; i += 1) {
            a[i] = get(i);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int frontIndex = arrayIndex(0);
        T returnItem = get(0);
        items[frontIndex] = null;
        nextFirst = frontIndex;
        size -= 1;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int backIndex = arrayIndex(size() - 1);
        T returnItem = get(size() - 1);
        items[backIndex] = null;
        nextLast = backIndex;
        size -= 1;
        return returnItem;
    }

    public void printDeque() {
       for (T item : this) {
           System.out.print(item + " ");
       }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[arrayIndex(index)];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (o instanceof Deque) {
            Deque<T> dq = (Deque<T>) o;
            if (size != dq.size()) { return false; }
            for (int i = 0; i < size; i++) {
                if (!get(i).equals(dq.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
