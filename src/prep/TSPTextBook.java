package prep;

import java.util.*;
public class TSPTextBook {
    public static void main(String[] args) {


        int[][] w = {
                {0, 3, 4, 2, 7},
                {3, 0, 4, 6, 3},
                {4, 4, 0, 5, 8},
                {2, 6, 5, 0, 6},
                {7, 3, 8, 6, 0}
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
            if ((mask & 1) == 0) continue;

            for (int i = 1; i < n; i++) {
                if ((mask & (1 << i)) == 0) continue;

                int prevMask = mask ^ (1 << i);
                if (prevMask == 0) continue;

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
        int best = INF;
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
            if (next == -1) break;
            path.add(next);
            mask ^= (1 << next);
            curr = next;
        }

        path.add(0);
        return path;
    }
}
