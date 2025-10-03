package temp;

import java.util.Arrays;

public class Linear_Binary_search {
    public static void main(String[] args) {
        int[] arr = find_BS(new int[]{1, 2, 3, 13});
        helper(arr);
        arr = find_BS(new int[]{1, 2, 3, 4});
        helper(arr);
    }

    public static int[] find(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] + arr[j] == 15)
                    return new int[]{arr[i], arr[j]};
            }
        }
        return null;
    }

    public static void helper(int arr[]) {
        if (arr == null) System.out.println("No elements with sum of 15 exist");
        else {
            for (int i = 0; i < 2; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static int[] find_BS(int arr[]) {
        Arrays.sort(arr);
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int currsum = arr[l] + arr[r];
            if (currsum == 15) return new int[]{arr[l], arr[r]};
            else if (currsum < 15) l = l + 1;
            else r = r - 1;
        }
        return null;
    }
}
