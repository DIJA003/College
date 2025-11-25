package prep;

import java.util.Arrays;

public class InplaceMergeSort {
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
        mergeInPlace(arr, left, mid, right);
    }

    private static void mergeInPlace(int[] arr, int start, int mid, int end) {
        int start2 = mid + 1;
        if (arr[mid] <= arr[start2]) return;
        while (start <= mid && start2 <= end){
            if(arr[start] <= arr[start2]) start++;
            else {
                int value = arr[start2];
                int index = start2;
                while(index > start){
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[start] = value;
                start++;
                mid++;
                start2++;
            }
        }
    }
}
