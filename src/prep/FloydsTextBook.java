package prep;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FloydsTextBook {
    public static int inf = 100000;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfVertices = in.nextInt();
        int numberOfEdges = in.nextInt();

        int[][] adj = setAdjMatrix(numberOfVertices,numberOfEdges);
        int[][] D = new int[numberOfVertices + 1][numberOfVertices + 1];
        int[][] P = new int[numberOfVertices + 1][numberOfVertices + 1];

        floyd(numberOfVertices+1,adj,D,P);

        System.out.println("from: ");
        int source = in.nextInt();
        System.out.println("to: ");
        int destination = in.nextInt();
        System.out.println("Adj. list: ");

        printAdj(adj);
        path(P,source,destination);

        //        List<List<Integer>>adj = new ArrayList<>();
//        for (int i = 0; i < numberOfVertices + 1; i++) {
//            adj.add(new ArrayList<>());
//        }
//        for (int i = 0; i < numberOfEdges; i++) {
//            int source = in.nextInt();
//            int destination = in.nextInt();
//            adj.get(source).add(destination);
//        }
//        int i = 0;
//        for(List<Integer> list : adj){
//            System.out.println(list.get(i++));
//        }


    }
    private static int[][] setAdjMatrix(int numberOfVertices, int numberOfEdges){
        int[][] adj = new int[numberOfVertices+1][numberOfVertices + 1];
        for (int i = 0; i < adj.length ; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                if(i != j){
                    adj[i][j] = inf;
                }
            }
        }
        for (int i = 0; i < numberOfEdges; i++) {
            int source = in.nextInt();
            int destination = in.nextInt();
            int cost = in.nextInt();
            adj[source][destination] = cost;
        }
        return adj;
    }

    private static void printAdj(int[][]adj){
        for (int i = 1; i < adj.length; i++) {
            for (int j = 1; j < adj[i].length; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void floyd(int n, int[][] w, int[][] d, int[][] p) {
        d = w;
        for(int k = 1; k < n; k++){
            for (int i = 1; i < n; i++){
                for (int j = 1; j < n; j++) {
                    if(d[i][k] + d[k][j] < d[i][j]){
                        p[i][j] = k;
                        d[i][j] = d[i][k]+d[k][j];
                    }
                }
            }
        }
    }
    private static void path(int[][] p, int source, int destination){
        if(p[source][destination] != 0){
            path(p,source,p[source][destination]);
            System.out.println("vertex number: " + p[source][destination]);
            path(p,p[source][destination],destination);
        }
    }
}
