public class UnionFind {
    // TODO: Instance variables
    int[] parent;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        parent = new int[N];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -parent(find(v));
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= parent.length || v < 0) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        if (parent(v) < 0) {
            return v;
        } else {
            parent[v] = find(parent(v));
            return parent[v];
        }

    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) {
            return;
        }

        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size2 < size1) {
            parent[root2] = root1;
            parent[root1] -= size2;
        } else {
            parent[root1] = root2;
            parent[root2] -= size1;
        }
    }

}
