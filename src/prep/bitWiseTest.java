package prep;
import java.util.*;
public class bitWiseTest {
    public static void main(String[] args){
        // getting all subsets of a set
        int[] arr = new int[10];
        List <List<Integer>> sol = new ArrayList<>();

        for(int i = 1; i <= 10; i++) arr[i-1] = i;
        int full = 1 << 10;
        for(int mask = 0; mask < full; mask++){
            List<Integer> curr = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                if((mask & (1<<i)) != 0) curr.add(arr[i]);
            }
            sol.add(curr);
        }
        System.out.println(sol.toString());
    }
}
