package prep;

import java.util.Arrays;

public class TextBookQuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,6,5,4,3,2,1};
        quicksort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quicksort(int[] S, int low, int high) {
        int pivotpoint;
        if (high > low) {
            pivotpoint = partition(S, low, high);
            quicksort(S, low, pivotpoint - 1);
            quicksort(S, pivotpoint + 1, high);
        }
    }

    private static int partition(int[] S, int low, int high){
        int i, j;
        int pivotitem = S[low];
        j = low;
        for (i = low + 1; i <= high; i++) {
            if(S[i] < pivotitem){
                j++;
                swap(S,i,j);
            }
        }
        swap(S,low,j);
        return j;
    }

    private static void swap(int[] S, int i, int j){
        int temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }
}
