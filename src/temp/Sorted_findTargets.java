package temp;

import java.util.ArrayList;
import java.util.List;

public class Sorted_findTargets {
    public static void main(String[] args){
        List<List<Integer>> li = find(new int[] {2,3,6,7},7);
        for(List<Integer>list :li){
            for(int num:list){
                System.out.print(num+" ");
            }
            System.out.println();
        }
    }
    public static List<List<Integer>> find(int[] arr, int target){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        backtrack(arr,target,0,0,sol,res);
        return res;
    }
    private static void backtrack(int[] arr, int target, int start, int currSum, List<Integer> sol, List<List<Integer>> res){
        if(currSum == target){
            res.add(new ArrayList<>(sol));
            return;
        }
        if(currSum > target || start == arr.length){
            return;
        }
        backtrack(arr,target,start+1,currSum,sol,res);
        sol.add(arr[start]);
        backtrack(arr,target,start,currSum+arr[start],sol,res);
        sol.removeLast();
    }
}
