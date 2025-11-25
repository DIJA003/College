package prep;

public class BinomialTextBook {
    public static void main(String[] args) {
        System.out.println(binomial2D(3, 2));
        System.out.println(binomial1D(3, 2));
    }

    private static int binomial2D(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    private static int binomial1D(int n, int k) {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[k];
    }
}
