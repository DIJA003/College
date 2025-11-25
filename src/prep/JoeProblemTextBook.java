package prep;

import java.util.*;

public class JoeProblemTextBook {
    public static void main(String[] args) {
        List<Integer> test = greed(new int[]{1, 5, 10, 25}, 11);
        System.out.println(test.toString());
    }

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
}
