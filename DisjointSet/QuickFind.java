/*
 * Union-Find Data Structure
 */

package DisjointSet;

public class QuickFind implements DisjointSet {
    int[] root;

    /**
     * <p>Time Complexity: O(n); where n = number of nodes
     * @param size length of disjoint set data structure
     */
    public QuickFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++)
            root[i] = i;
    }

    /**
     * <p>Time Complexity: O(1)
     * @return root node of x
     */
    public int find(int x) {
        return root[x];
    }

    /**
     * <p>Time Complexity: O(n); where n = number of nodes
     * <p>Union sets having {@code x} and having {@code y}
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
     * <p>Time Complexity: O(1)
     * @return whether node {@code x} and node {@code y} are connected
     */
    public boolean areConnected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        QuickFind qf = new QuickFind(10);

        // 1-2-5-6-7 3-8-9 4
        qf.union(1, 2);
        qf.union(2, 5);
        qf.union(5, 6);
        qf.union(6, 7);
        qf.union(3, 8);
        qf.union(8, 9);
        System.out.println(qf.areConnected(1, 5)); // true
        System.out.println(qf.areConnected(5, 7)); // true
        System.out.println(qf.areConnected(4, 9)); // false
        // 1-2-5-6-7 3-8-9-4
        qf.union(9, 4);
        System.out.println(qf.areConnected(4, 9)); // true
    }
}
