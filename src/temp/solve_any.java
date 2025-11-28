package temp;

import java.lang.reflect.Array;
import java.util.*;

public class solve_any {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
//        List<Integer> list = solve(new int[]{10, 2, 4, 7});
//        for (int i : list) System.out.print(i + " ");
//        System.out.println();
//        list = solve(new int[]{5, 1, 4, 9, 8, 0, 1});
//        for (int i : list) System.out.print(i + " ");
//        System.out.println();
//        list = solve(new int[]{1, 2, 3, 4, 5});
//        for (int i : list) System.out.print(i + " ");

//        int[] arr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
//        System.out.println("Before sort: " + Arrays.toString(arr));
//        mergeSort(arr);
//        System.out.println("After sort: " + Arrays.toString(arr));

//        System.out.println(1<<5);


    }
    //{5,1,4,9,8,0,1}
    //{1,2,3,4,5}

    // minimum number of elements to delete to leave
    // only elements of equal value
    public static int equalizeArray(int[] arr){
        int n = arr.length;
        int[] freq = new int[101];
        for(int i : arr) freq[i]++;
        int ref = 0;
        for(int i : freq) ref = Math.max(ref,i);
        return n - ref;
    }

    //
    private static void manual_sort(int[] arr) {
        int[] freq = new int[1001];
        for (int num : arr) {
            freq[num]++;
        }
        int k = 0;
        for (int i = 0; i <= 1000; i++) {
            while (freq[i] > 0) {
                arr[k++] = i;
                freq[i]--;
            }
        }

    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static int sumOfInt(int s) {
        if (s == 0) return 0;
        return s % 10 + sumOfInt(s / 10);
    }

    private static int reverse(int s) {
        int rev = 0;
        while (s != 0) {
            rev += s % 10;
            rev *= 10;
            s /= 10;
        }
        return rev / 10;
    }

    private static int rev(int num) {
        if (num == 0) return 0;
        return num % 10 * 10 + rev(num / 10);
    }

    private static List<Integer> solve(int[] arr) {
        if (arr.length <= 1) return null;
        int left = 0, right = 1, len = arr.length - 1;
        int currSum = 0, maxSum = 0;
        List<Integer> ans = new ArrayList<>();
        while (right <= len) {
            currSum = arr[left] + arr[right];
            if (currSum > maxSum) {
                maxSum = currSum;
                ans.add(maxSum);
            }
            currSum = 0;
            right++;
            left++;
        }
        maxSum = 0;
        for (int i : ans) {
            maxSum = Math.max(maxSum, i);
        }
        for (int i = 0; i < len + 1; i++) {
            for (int j = 1; j < len + 1; j++) {
                if (arr[j] == maxSum - arr[i]) {
                    ans.clear();
                    ans.add(arr[i]);
                    ans.add(arr[j]);
                    return ans;
                }
            }
        }
        return ans;
    }

    private static List<Integer> solve2(int[] arr) {
        if (arr.length <= 1) return new ArrayList<>(arr[arr.length - 1]);
        Arrays.sort(arr);
        List<Integer> ans = new ArrayList<>();
        int start = arr.length - 1;
        int end = start - 1, currSum = 0, maxSum = 0;
        while (end >= 0) {
            currSum = arr[start] + arr[end];
            if (currSum >= maxSum) {
                maxSum = currSum;
            }
            int restSum = 0;
            for (int i = 0; i < end; i++) {
                restSum += arr[i];
            }
            if (restSum <= maxSum) {
                ans.add(arr[start]);
                ans.add(arr[end]);
                end--;
            }


        }
        return null;
    }

//    private static int maxDiff(int arr[], int left, int right){
//        if(left == right) return arr[left];
//        int mid = left + (right - left) / 2;
//        int l = maxDiff(arr,left,mid);
//        int r = maxDiff(arr,mid+1,right);
//        int diff = l
//    }


    private static void mergeSort(int[] arr) {
        int length = arr.length;
        if (length < 3) return;

        int partition = length / 3;
        int mid = ((partition + 1) + length) / 2;
        int[] leftHalf = Arrays.copyOfRange(arr, 0, partition);
        int[] midHalf = Arrays.copyOfRange(arr, partition, mid);
        int[] rightHalf = Arrays.copyOfRange(arr, mid, length);

        mergeSort(leftHalf);
        mergeSort(midHalf);
        mergeSort(rightHalf);
        merge(arr, leftHalf, midHalf, rightHalf);
    }

    private static void merge(int[] arr, int[] leftHalf, int[] midHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int midSize = midHalf.length;
        int rightSize = rightHalf.length;
        int tempLen = leftSize + midSize;

        int[] temp = new int[tempLen];

        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < midSize) {
            if (leftHalf[i] <= midHalf[j]) {
                temp[k] = midHalf[i++];
            } else {
                temp[k] = midHalf[j++];
            }
            k++;
        }
        while (i < leftSize) {
            temp[k++] = leftHalf[i++];

        }
        while (j < midSize) {
            temp[k++] = rightHalf[j++];

        }
        k = 0;
        i = 0;
        j = 0;

        while (i < rightSize && j < tempLen) {
            if (rightHalf[i] <= temp[j]) {
                arr[k] = rightHalf[i++];
            } else {
                arr[k] = temp[j++];
            }
            k++;
        }
        while (j < tempLen) {
            arr[k++] = temp[j++];
        }
        while (i < rightSize) {
            arr[k++] = temp[i++];
        }

    }

}
