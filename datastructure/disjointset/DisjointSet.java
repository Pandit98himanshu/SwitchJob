package datastructure.disjointset;

public interface DisjointSet {
    public int find(int x);
    public void union(int x, int y);
    public boolean areConnected(int x, int y);
}
