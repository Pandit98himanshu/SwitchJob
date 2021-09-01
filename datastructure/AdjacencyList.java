package datastructure;

import java.util.*;

public class AdjacencyList {
    private List<Integer>[] adjList;

    public AdjacencyList(int vertices) {
        this.adjList = new ArrayList[vertices];
    }

    /**
     * @return adjacency list from given array "directedEdges"
     */
    public List<Integer>[] createAdjacencyList(int[][] directedEdges) {
        int n = adjList.length;
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = adjList[directedEdges[i][0]];
            if (list == null)
                list = new ArrayList<>();
            list.add(directedEdges[i][1]);
            adjList[directedEdges[i][0]] = list;
        }
        return adjList;
    }
}
