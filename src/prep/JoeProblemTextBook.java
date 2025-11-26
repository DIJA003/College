package prep;

import java.util.*;

public class JoeProblemTextBook {
    public static void main(String[] args) {
//        List<Integer> test = greed(new int[]{1, 5, 10, 25}, 11);
//        System.out.println(test.toString());
        int test = change(new int[]{1, 5, 10, 25}, 11);
        System.out.println(test);
    }

    //this is for greedy approach to find the least coins to reach the amount
    public static List<Integer> greed(int[] coins, int amount) {
        Arrays.sort(coins);
        List<Integer> change = new ArrayList<>();
        int remain = amount;

        for (int i = coins.length - 1; i >= 0; --i) {
            while (coins[i] <= remain) {
                change.add(coins[i]);
                remain -= coins[i];
            }
        }
        return change;
    }

    //this is for dp getting the fewest number of coins
    public static int change(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //this is for number of ways to reach the amount
    public static int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; ++i) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
