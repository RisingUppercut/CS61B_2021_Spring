package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private int getIndex(K key) {
        return Math.floorMod(key.hashCode(), numOfBuckets);
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        numOfElements = 0;
        for (Collection<Node> b: buckets) {
            b.clear();
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int idx = getIndex(key);
        for (Node p : buckets[idx]) {
            if (key.equals(p.key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int idx = getIndex(key);
        for (Node p : buckets[idx]) {
            if (key.equals(p.key)) {
                return p.value;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return numOfElements;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        int idx = getIndex(key);
        for (Node p : buckets[idx]) {
            if (key.equals(p.key)) {
                p.value = value;
                return;
            }
        }
        buckets[idx].add(createNode(key, value));
        numOfElements += 1;
        if ((double) numOfElements / numOfBuckets >= loadFactor) {
            resize(numOfBuckets * 2);
        }
    }

    private void resize(int newsize) {
        numOfBuckets = newsize;
        Collection<Node>[] newbuckets = createTable(newsize);
        for (Collection<Node> b : buckets) {
            for (Node p : b) {
                int idx = getIndex(p.key);
                newbuckets[idx].add(p);
            }
        }
        buckets = newbuckets;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Collection<Node> b : buckets) {
            for (Node p : b) {
                keys.add(p.key);
            }
        }
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        V val = null;
        int idx = getIndex(key);
        for (Node p : buckets[idx]) {
            if (key.equals(p.key)) {
                val = p.value;
                buckets[idx].remove(p);
                break;
            }
        }
        return val;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     *
     * @param key
     * @param value
     */
    @Override
    public V remove(K key, V value) {
        V val = get(key);
        if (val.equals(value)) {
            remove(key);
        }
        return val;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int numOfElements = 0;
    private int numOfBuckets;
    private double loadFactor;
    /** Constructors */
    public MyHashMap() {
        numOfBuckets = 16;
        loadFactor = 0.75;
        buckets = createTable(numOfBuckets);
    }

    public MyHashMap(int initialSize) {
        this.numOfBuckets = initialSize;
        this.loadFactor = 0.75;
        buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.numOfBuckets = initialSize;
        this.loadFactor = maxLoad;
        buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
