/*
 * Union-Find Data Structure
 */

package DisjointSet;

public class QuickUnion {
    int[] root;

    /**
     * Time Complexity: O(n); where n = number of nodes
     * @param size length of disjoint set data structure
     */
    public QuickUnion(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++)
            root[i] = i;
    }

    /**
     * Time Complexity: O(n); where n = number of nodes
     * @return root node of {@code x}
     */
    public int find(int x) {
        return root[x];
    }

    /**
     * Time Complexity: O(n); where n = number of nodes
     * Union sets having {@code x} and having {@code y}
     * and makes root of {@code x} as root of both sets
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;

        for (int i = 0; i < root.length; i++)
            if(root[i] == rootY)
                root[i] = rootX;
    }

    /**
     * Time Complexity: O(1)
     * @return whether node {@code x} and node {@code y} are connected
     */
    public boolean areConnected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(10);

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
