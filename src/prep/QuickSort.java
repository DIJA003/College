package prep;

import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int [] arr = new int [] {6,5,4,3,2,1};
        for(int i : arr)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        quickSort(arr);
        for(int i : arr)
        {
            System.out.print(i + " ");
        }
    }

    private static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivotIndex = new Random().nextInt(high - low) + low;
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        int lp = partition(arr,low,high,pivot);

        quickSort(arr, low, lp - 1);
        quickSort(arr, lp + 1, high);
    }
    private static int partition(int [] arr, int lowIndex, int highIndex, int pivot){
        int lp = lowIndex, rp = highIndex-1;
        while (lp < rp) {
            while (arr[lp] <= pivot && lp < rp) {
                lp++;
            }
            while (arr[rp] >= pivot && lp < rp) {
                rp--;
            }
            swap(arr, lp, rp);
        }
        if(arr[lp] > arr[highIndex]) {
            swap(arr, lp, highIndex);
        }
        else {
            lp = highIndex;
        }
        return lp;
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
