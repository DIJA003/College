package prep;

import java.util.Arrays;

public class TextBookMergeSort2 {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge2(arr, left, mid, right);
    }

    private static void merge2(int[] S, int left, int mid, int right) {
        int[] U = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while(i <= mid && j <= right){
            if(S[i] < S[j]) U[k++] = S[i++];
            else U[k++] = S[j++];
        }

        while(i <= mid) U[k++]= S[i++];
        while(j <= right) U[k++] = S[j++];

        for (k = 0; k < U.length; k++) {
            S[left+k] = U[k];
        }

    }
}
