package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static java.lang.Math.min;

public class Kruskals {

    public static void main(String[] args) {
//        int[][] adjacencyMatrix = {
//                {0, 7, 0, 0, 0},
//                {7, 0, 9, 14, 0},
//                {0, 9, 0, 12, 0},
//                {0, 14, 12, 0, 8},
//                {0, 0, 0, 8, 0},
//        };
        int[][] adjacencyMatrix = {
                {0, 0, 0, 8, 0, 0, 0, 0},
                {0, 0, 2, 0, 0, 0, 0, 6},
                {0, 2, 0, 0, 0, 0, 0, 4},
                {8, 0, 0, 0, 16, 0, 0, 12},
                {0, 0, 0, 16, 0, 0, 0, 13},
                {0, 0, 0, 0, 0, 0, 11, 7},
                {0, 0, 0, 0, 0, 11, 0, 9},
                {0, 6, 4, 12, 13, 7, 9, 0},
        };

        int[][] minimumSpanningTree = kruskals(adjacencyMatrix);
        System.out.println("Minimum Spanning Tree:");
        for (int[] row : minimumSpanningTree) {
            System.out.println(Arrays.toString(row));
        }
    }

    //Next, implement Kruskal’s algorithm in code. Your
    //implementation should accept a weighted adjacency matrix (int[][]) as input, and return the int[][]
    //weighted adjacency matrix of the minimum spanning tree as output (or null if no spanning tree exists
    //because the graph wasn’t fully connected).
    public static int[][] kruskals(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // reflective property of matrix
                if (adjacencyMatrix[i][j] > 0)
                    edges.add(new Edge(i, j, adjacencyMatrix[i][j]));
            }
        }

        int[] conns = new int[n];
        for(int i = 0; i < n; i++) {
            conns[i] = i;
        }

        PriorityQueue<Edge> cands = edges; // copy edges
        List<Edge> e1 = new ArrayList<>();

        Edge e;
        while (!cands.isEmpty() && e1.size() < n -1) {
            e = cands.poll();
            int a = e.endpointOne;
            int b = e.endpointTwo;

            int lowA = findLowest(conns, a);
            if (lowA == 0)
                lowA = a;

            int lowB = findLowest(conns, b);
            if (lowB == 0)
                lowB = b;

            if (lowA != lowB)
                e1.add(e);

            conns[a] = min(lowA, lowB);
            conns[b] = min(lowA, lowB);
        }

        if(e1.size() < n-1)
            return null;

        int[][] mstAdjacencyMatrix = new int[n][n];
        for (Edge edge : e1) {
            mstAdjacencyMatrix[edge.endpointOne][edge.endpointTwo] = edge.weight;
            mstAdjacencyMatrix[edge.endpointTwo][edge.endpointOne] = edge.weight;
        }
        return mstAdjacencyMatrix;
    }

    private static int findLowest(int[] conns, int id) {
        if(conns[id] == id) {
            return id;
        } else {
            return findLowest(conns, conns[id]);
        }
    }

    private static class Edge implements Comparable<Edge>{
        int endpointOne;
        int endpointTwo;
        int weight;

        public Edge(int endpointOne, int endpointTwo, int weight) {
            this.endpointOne = endpointOne;
            this.endpointTwo = endpointTwo;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

}