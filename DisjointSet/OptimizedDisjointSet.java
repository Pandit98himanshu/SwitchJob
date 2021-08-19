/*
 * Union-Find Data Structure
 */

package DisjointSet;

/**
 * More efficient than {@link QuickFind}, {@link QuickUnion} & {@link UnionByRank}
 */
public class OptimizedDisjointSet implements DisjointSet {
    public int[] root;
    public int[] rank;         // height of each vertex
    /**
     * <p>Time Complexity: O(n); where n = number of nodes
     * @param size length of disjoint set data structure
     */
    public OptimizedDisjointSet(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * <strong>Path Compression</strong>
     * <p>We don't need to find the root for same node again.
     * Once we find the root node of {@code x}, we'll update the root node
     * hence, next time when we'll search for the root node of {@code x}
     * it will be an {@code O(1)} operation.
     *
     * <p>Basically, we plucked all nodes from tree and join directly to the root node
     * <p>Time Complexity: O(𝛂(n)); where 𝛂(n) = O(1) on average
     * @return root node of {@code x}
     */
    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    /**
     * <strong>Union By Rank</strong>
     * <p>Time Complexity: O(𝛂(n)); where 𝛂(n) = O(1) on average
     * Union sets having node {@code x} and node {@code y}
     * and makes root based on the height of the tree i.e., by rank
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        // make root which has larger length
        // because by this method our tree will not become a line graph
        if (rank[rootX] < rank[rootY])
            root[rootX] = rootY;
        else if (rank[rootY] < rank[rootX])
            root[rootY] = rootX;
        else {
            root[rootY] = rootX;
            rank[rootX]++;
        }
    }

    /**
     * <p>Time Complexity: O(𝛂(n)); where 𝛂(n) = O(1) on average
     * @return Whether node {@code x} and node {@code y} are connected
     */
    public boolean areConnected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        OptimizedDisjointSet qu = new OptimizedDisjointSet(10);

        // 1-2-5-6-7 3-8-9 4
        qu.union(1, 2);
        qu.union(2, 5);
        qu.union(5, 6);
        qu.union(6, 7);
        qu.union(3, 8);
        qu.union(8, 9);
        System.out.println(qu.areConnected(1, 5)); // true
        System.out.println(qu.areConnected(5, 7)); // true
        System.out.println(qu.areConnected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        qu.union(9, 4);
        System.out.println(qu.areConnected(4, 9)); // true
    }
}
