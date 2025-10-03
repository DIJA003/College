package temp;

import java.util.HashSet;

public class Unsorted_findTargets {
    public int[] find(int[] arr, int target){
        HashSet<Integer> set = new HashSet<>();
        for(int num : arr){
            int diff = target - num;
            if(set.contains(diff)){
                return new int[] {num,diff};
            }
            set.add(num);
        }
        return null;
    }
    
    public int[] findTwoNumbers(int[] arr,int target){
        int[] ans = find(arr,target);
        if(ans != null) return ans;
        return new int []{-1};
    }
}
