package temp;

public class algo_lab4 {
    public static void main(String[] args) {
        //System.out.println(max_linear(new int[]{1, 2, 3, 4, 9, 19, 2}, 0));
        int[] arr = {1, 2, 3, 4, 9, 19, 22, 0};
        System.out.println(max_binary(arr, 0, arr.length - 1));
    }

    private static int max_linear(int[] arr, int i) {
        if (i == arr.length - 1) return arr[i];
        return Math.max(max_linear(arr, i + 1), arr[i]);
    }

    private static int max_binary(int[] arr, int l, int r) {
        if (l == r) return arr[l];
        int mid = l + (r - l) / 2;
        int rightMax = max_binary(arr, l, mid);
        int leftMax = max_binary(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }
}
