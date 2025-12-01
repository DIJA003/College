package prep;

import java.util.*;
public class KruskalTextBook {
    public static void main(String[] args){

    }
    static class Node{
        int parent;
        int depth;
        int smallest;
        Node(int index){
            parent = index;
            depth = 0;
            smallest = index;
        }
    }
    static class Edge implements Comparable<Edge>{
        int from,to,cost;
        Edge(int from,int to, int cost){
            this.from = from;
            this.cost = cost;
            this.to = to;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }
    public static class DSU{
        static Node[] u;
        static void makeSet(int i){
            u[i] = new Node(i);
        }
        static int find(int i){
            while(u[i].parent != i){
                i = u[i].parent;
            }
            return i;
        }
        static void merge(int p, int q){
            if(u[p].depth == u[q].depth){
                u[p].depth = u[p].depth + 1;
                u[q].parent = p;

                if(u[q].smallest < u[p].smallest) u[p].smallest = u[q].smallest;
            } else if(u[p].depth < u[q].depth){
                u[p].parent = q;
                if(u[p].smallest < u[q].smallest) u[q].smallest = u[p].smallest;
            } else {
                u[q].parent = p;
                if(u[q].smallest < u[p].smallest) u[p].smallest = u[q].smallest;
            }
        }
        static int small(int p){
            return u[p].smallest;
        }

        static List<Edge> kruskal(int n, List<Edge> E){
            Collections.sort(E);
            u = new Node[n + 1];
            for(int i = 1; i <= n; i++) makeSet(i);
            List<Edge> f = new ArrayList<>();

            for(Edge e : E){
                if(f.size() == n -1) break;
                int i = e.from;
                int j = e.to;
                int p = find(i);
                int q = find(j);
                if(p != q){
                    merge(p,q);
                    f.add(e);
                }
            }
            return f;
        }

    }


}
