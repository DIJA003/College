package prep;

import java.util.LinkedList;
import java.util.List;

public class BucketSort {
    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,6,5,4,32,1,100,2,123,200,50,30,40,50,20,25,55,65};
        bucketSort(arr,1);
        for(int i : arr) System.out.print(i+" ");
    }

    private static void bucketSort(int[] arr, int bucketSize) {

        if (arr == null || arr.length == 0 || bucketSize < 0) {
            return;
        }
        int len = arr.length;
        int min = arr[0], max = arr[0];
        for(int i = 1; i < len; i++){
            if(arr[i] > max) max = arr[i];
            if(arr[i] < min) min = arr[i];
        }
        double range = Math.ceil((double)(max - min) +1) / bucketSize;
        List<Integer>[] buckets = new LinkedList[bucketSize];
        for(int i = 0; i < bucketSize; i++){
            buckets[i] = new LinkedList<>();
        }
        for(int value : arr){
            int bucketIndex = (int) ((value - min) / range);
            buckets[bucketIndex].add(value);
        }
        int index = 0;
        for(List<Integer> bucket : buckets){
            insertionSort(bucket);
            for(int num : bucket){
                arr[index++] = num;
            }
        }

    }
    private static void insertionSort(List<Integer> bucket){
        int size = bucket.size();
        for(int i = 1; i < size; i++){
            int key = bucket.get(i);
            int j = i - 1;
            while(j>=0 && bucket.get(j) > key){
                bucket.set(j+1,bucket.get(j));
                j--;
            }
            bucket.set(j+1,key);
        }
    }
}
