package prep;

import java.util.*;
public class TSPTextBook {
    public static void main(String[] args) {
        int inf = 99999;

        int[][] w = {
                {0, 2, 9, inf},
                {1, 0, 6, 4},
                {inf, 7, 0, 8},
                {6, 3, inf, 0}
        };



        int n = w.length;
        int[][] P = new int[n][1 << n];


        for (int[] row : P) Arrays.fill(row, -1);

        int cost = TSP(n, w, P);
        List<Integer> path = getPath(n, P);

        System.out.println("Minimum Cost = " + cost);
        System.out.println("Path = " + path);
    }


    public static int TSP(int n, int[][] w, int[][] p) {
        int INF = 999999;
        int FULL = 1 << n;
        int[][] dp = new int[n][FULL];


        for (int i = 0; i < n; i++) Arrays.fill(dp[i], INF);


        dp[0][1] = 0;


        for (int mask = 1; mask < FULL; mask++) {

            for (int i = 1; i < n; i++) {

                int prevMask = mask ^ (1 << i);
                if (prevMask == 0) continue;
                //System.out.println(prevMask);
                for (int j = 0; j < n; j++) {
                    if ((prevMask & (1 << j)) == 0) continue;

                    int candidate = dp[j][prevMask] + w[j][i];
                    if (candidate < dp[i][mask]) {
                        dp[i][mask] = candidate;
                        p[i][mask] = j;
                    }
                }
            }
        }

        int mask = FULL - 1;
        int best = Integer.MAX_VALUE;
        for (int j = 1; j < n; j++) {
            int candidate = dp[j][mask] + w[j][0];
            if (candidate < best) {
                best = candidate;
                p[0][mask] = j;
            }
        }

        return best;
    }

    public static List<Integer> getPath(int n, int[][] p) {
        int mask = (1 << n) - 1;
        List<Integer> path = new ArrayList<>();

        int curr = 0;
        path.add(curr);

        while (true) {
            int next = p[curr][mask];
            System.out.println(next);
            if (next == -1) break;
            path.add(next);
            mask ^= (1 << next);
            curr = next;
        }

        path.add(0);
        return path;
    }
}
