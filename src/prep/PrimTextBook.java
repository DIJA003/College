package prep;

import java.util.*;


//a3mlha bel priority queue
public class PrimTextBook {
    public static void main(String[] args){
        int[][] w = {
                {0, 2, 3, 1, 4},
                {2, 0, 5, 2, 3},
                {3, 5, 0, 4, 1},
                {1, 2, 4, 0, 6},
                {4, 3, 1, 6, 0}
        };

        List<Edge> ans = prim(5,w);
        int totalCost = 0;
        for (Edge e : ans) {
            System.out.println(e);
            totalCost += e.cost;
        }
        System.out.println("Total weight: " + totalCost);
    }

    public static class Edge{
        int from, to, cost;
        Edge(int from, int to, int cost){this.from = from; this.to = to; this.cost = cost;}

        @Override
        public String toString() {
            return String.format("Edge: from %d to %d with cost of %d",from,to,cost);
        }
    }

    public static List<Edge> prim(int n, int[][] w){
        int[] nearest = new int[n];
        int[] distance = new int[n];
        boolean[] seen = new boolean[n];

        List<Edge> f = new ArrayList<>();

        seen[0] = true;
        for (int i = 1; i < n; i++) {
            nearest[i] = 0;
            distance[i] = (w[0][i] == 0) ? Integer.MAX_VALUE : w[0][i];
        }
        for(int edge = 0; edge < n - 1; ++edge){
            int min = Integer.MAX_VALUE;
            int vNear = -1;

            for (int i = 1; i < n; i++) {
                if(!seen[i] && distance[i] < min){
                    min = distance[i];
                    vNear = i;
                }
            }
            f.add(new Edge(vNear,nearest[vNear],w[vNear][nearest[vNear]]));
            seen[vNear] = true;

            for (int i = 1; i < n; i++) {
                if(!seen[i] && w[i][vNear] < distance[i]){
                    distance[i] = w[i][vNear];
                    nearest[i] = vNear;
                }
            }
        }
        return f;
    }
}
