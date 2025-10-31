package temp;

import java.util.*;

public class solve_any {
    public static void main(String[] args) {
        System.out.println(reverse(12345));
    }

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
        return rev/10;
    }
    private static int rev(int num){
        if(num == 0) return 0;
        return num % 10 * 10 + rev(num/10);
    }


}
