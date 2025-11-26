package prep;

import java.util.*;


//a3mlha bel priority queue
public class PrimTextBook {
    public static void main(String[] args) {
        int n = 5;

        int[][] w = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };
        System.out.println(prim(n, w));
        System.out.println(prim3(n,w));

        List<List<Edge2>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 3, 6);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 1, 3, 8);
        addEdge(graph, 1, 4, 5);
        addEdge(graph, 2, 4, 7);
        addEdge(graph, 3, 4, 9);

        System.out.println(prim2(n, graph));
    }
//---------------------------------------------------------------------------------------------------------------------
    //using textbook sudo code
    public static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("Edge: from %d to %d with cost of %d", from, to, cost);
        }
    }// stand for the set F

    public static int prim(int n, int[][] w) {
        int[] nearest = new int[n];
        int[] distance = new int[n];
        boolean[] seen = new boolean[n];

        int ans = 0;

        List<Edge> f = new ArrayList<>();

        seen[0] = true;
        for (int i = 1; i < n; i++) {
            nearest[i] = 0;
            distance[i] = (w[0][i] == 0) ? Integer.MAX_VALUE : w[0][i];
        }
        for (int edge = 0; edge < n - 1; ++edge) {
            int min = Integer.MAX_VALUE;
            int vNear = -1;

            for (int i = 1; i < n; i++) {
                if (!seen[i] && distance[i] < min) {
                    min = distance[i];
                    vNear = i;
                }
            }
            f.add(new Edge(vNear, nearest[vNear], w[vNear][nearest[vNear]]));
            seen[vNear] = true;

            for (int i = 1; i < n; i++) {
                if (!seen[i] && w[i][vNear] != 0 && w[i][vNear] < distance[i]) {
                    distance[i] = w[i][vNear];
                    nearest[i] = vNear;
                }
            }
        }
        for (Edge e : f) {
            ans += e.cost;
        }
        return ans;
    }
    public static void showMST(List<Edge> MST){
        for (Edge e : MST) {
            System.out.println(e);
        }
    }
//------------------------------------------------------------------------------------------------------------------

    //with priority queue but no weighted matrix
    public static class Edge2 {
        int node, cost;

        public Edge2(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    } // stand for set F

    public static void addEdge(List<List<Edge2>> graph, int from, int to, int cost) {
        graph.get(from).add(new Edge2(to, cost));
        graph.get(to).add(new Edge2(from, cost));
    }

    public static int prim2(int n, List<List<Edge2>> graph) {
        boolean[] inMst = new boolean[n];
        PriorityQueue<Edge2> pq = new PriorityQueue<>(Comparator.comparing(e -> e.cost));
        int ans = 0;

        pq.add(new Edge2(0, 0));

        while (!pq.isEmpty()) {
            Edge2 e = pq.poll();
            if (inMst[e.node]) continue;
            inMst[e.node] = true;
            ans += e.cost;
            for (Edge2 nxt : graph.get(e.node)) {
                if (!inMst[nxt.node]) {
                    pq.add(new Edge2(nxt.node, nxt.cost));
                }
            }
        }
        return ans;
    }
//----------------------------------------------------------------------------------------------------------------------
    //with priority queue but with weighted matrix using edge2 suppose if no edge between 2 vertices then weight equals zero
    public static int prim3(int n, int[][] w) {
        boolean[] inMst = new boolean[n];
        PriorityQueue<Edge2> pq = new PriorityQueue<>(Comparator.comparing(e -> e.cost));

        int ans = 0;

        pq.add(new Edge2(0,0));

        while(!pq.isEmpty()){
            Edge2 e = pq.poll();
            if(inMst[e.node]) continue;

            inMst[e.node] = true;
            ans += e.cost;

            for(int v = 0; v < n; ++v){
                if(!inMst[v] && w[e.node][v] > 0){
                    pq.add(new Edge2(v,w[e.node][v]));
                }
            }
        }
        return ans;
    }

}
