package prep;

public class MergeSort {
    public static void main(String[] args) {
        int [] arr = new int [] {6,5,4,3,2,1};
        for(int i : arr)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        mergeSort(arr);
        for(int i : arr)
        {
            System.out.print(i + " ");
        }
    }

    private static void mergeSort(int[] arr) {
        int length = arr.length;
        if (length < 2) return;

        int mid = length / 2;
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[length - mid];

        for (int i = 0; i < mid; i++) {
            leftHalf[i] = arr[i];
        }
        for (int i = mid; i < length; i++) {
            rightHalf[i - mid] = arr[i];
        }
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        merge(arr,leftHalf,rightHalf);
    }

    private static void merge(int[] arr, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if(leftHalf[i] <= rightHalf[j]){
                arr[k] = leftHalf[i++];
            }
            else {
                arr[k] = rightHalf[j++];
            }
            k++;
        }
        while(i < leftSize){
            arr[k++] = leftHalf[i++];
        }
        while(j < rightSize){
            arr[k++] = rightHalf[j++];
        }
    }
}
