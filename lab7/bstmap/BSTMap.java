package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends  Comparable<K>, V> implements  Map61B<K, V>{

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return root.find(key) != null;
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        BSTNode lookUp = root.find(key);
        if (lookUp != null) {
            return lookUp.val;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root != null) {
            BSTNode lookUp = root.find(key);
            if (lookUp != null) {
                lookUp.val = value;
            } else {
                put(root, key, value);
            }
        } else {
            root = new BSTNode(key, value, null , null);
        }
    }

    private BSTNode put(BSTNode n, K k, V v) {
        if (n == null) {
            n = new BSTNode(k, v, null, null);
        }
        if (k.compareTo(n.key) < 0) {
            n.leftChild = put(n.leftChild, k, v);
        } else if (k.compareTo(n.key) > 0) {
            n.rightChild = put(n.rightChild, k, v);
        }
        return n;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInorder() {
        printInorder(root);
    }

    private void printInorder(BSTNode p) {
        if (p == null) {
            return;
        }
        if (p.leftChild == null) {
            System.out.print(p.key + " ");
        }
        printInorder(p.leftChild);
        System.out.print(p.key + " ");
        printInorder(p.rightChild);
    }

    private class BSTNode {
        K key;
        V val;
        BSTNode leftChild;
        BSTNode rightChild;
        BSTNode(K k, V v, BSTNode lc, BSTNode rc) {
            key = k;
            val = v;
            leftChild = lc;
            rightChild = rc;
        }

        BSTNode find(K sk) {
            if (sk.equals(key)) {
                return this;
            } else if (leftChild != null && sk.compareTo(key) < 0) {
                return leftChild.find(sk);
            } else if (rightChild != null && sk.compareTo(key) > 0) {
                return rightChild.find(sk);
            }
            return null;
        }
    }

    private BSTNode root;
    private int size = 0;

}
