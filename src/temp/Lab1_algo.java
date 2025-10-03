package temp;

public class Lab1_algo {
    public static void main(String[] args) {
        System.out.println("First Method: " + maxSubArr(new int[] {5,4,-1,7,8}));
        System.out.println("second Method: " + MaxSubArray(new int[] {5,4,-1,7,8}));
    }

    static int maxSubArr(int[] arr) {
        int currMax = arr[0], currSum = arr[0];
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            currSum = Math.max(arr[i], currSum + arr[i]);
            currMax = Math.max(currMax, currSum);
        }

        return currMax;

    }
    static int MaxSubArray(int[] arr){
        return divAndConq(arr,0,arr.length-1);
    }

    static int divAndConq(int[] arr, int left, int right) {
        if(left == right) return arr[left];
        int mid = left + (right - left) / 2;
        int leftMax = divAndConq(arr,left,mid);
        int rightMax = divAndConq(arr,mid+1,right);
        int intersection = maxSum(arr,left,mid,right);
        return Math.max(Math.max(leftMax,rightMax),intersection);
    }
    static int maxSum(int [] arr, int left, int mid, int right){
        int leftMax = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = mid; i >= left;i--){
            sum+=arr[i];
            leftMax = Math.max(leftMax,sum);
        }
        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for(int i = mid + 1; i <= right; i++){
            sum+=arr[i];
            rightMax = Math.max(sum,rightMax);
        }
        return leftMax+rightMax;
    }
}
