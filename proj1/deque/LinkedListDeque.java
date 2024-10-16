package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;
        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node currentNode;
        public LinkedListDequeIterator() {
            currentNode = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return currentNode != sentinel;
        }

        @Override
        public T next() {
            T returnItem = currentNode.item;
            currentNode = currentNode.next;
            return returnItem;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            Node p = sentinel.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    private T getRecursive(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return getRecursive(sentinel.next, index);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (o instanceof LinkedListDeque) {
            LinkedListDeque lld = (LinkedListDeque) o;
            if (size != lld.size()) { return false; }
            for (int i = 0; i < size; i++) {
                if (get(i) != lld.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

