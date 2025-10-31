package temp;

import java.util.ArrayList;
import java.util.HashSet;

public class questions {
    public static void main(String[] args) {

    }




    private static void remove_dup(int[] arr) {
        HashSet<Integer> st = new HashSet<>();
        int len = arr.length;
        for (int i : arr) {
            st.add(i);
        }
        int i = 0;
        for (int j : st) {
            if (i < st.size()) {
                arr[i] = j;
                i++;
            }
        }
        for (int k = i; k < len; k++) {
            arr[k] = 0;
        }
    }

    private static void print_arr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void move_zeros(int[] arr) {
        int len = arr.length;
        int[] temp = new int[len];
        int k = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] != 0) {
                temp[k++] = arr[i];
            }
        }
        for (int i = 0; i < len; i++) {
            arr[i] = temp[i];
        }

    }

    private static void intersect(int[] arr, int[] arr2) {
        int len1 = arr.length, len2 = arr2.length;
        HashSet<Integer> st = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < len1; i++) {
            st.add(arr[i]);
        }
        for (int i = 0; i < len2; i++) {
            if (st.contains(arr2[i])) {
                list.add(arr2[i]);
            }
        }
        print_arr(list);
    }

    private static void print_arr(ArrayList<Integer> list) {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();


    }

    public static int find_place(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return l;
    }

    public static String revers_words(String st){
        char[] arr = st.toCharArray();
        int len = arr.length;
        reverse(arr,0,len);

        int i = 0;
        for(int l = 0; l < len; l++){
            if(arr[l] != ' '){
                if(i != 0){
                    arr[i++] = ' ';
                }
                int r = l;
                while(r < len && arr[r] != ' '){
                    arr[i++] = arr[r++];
                }
                reverse(arr,i-(r-l),i);
                l = r;
            }
        }
        return new String(arr,0,i);
    }
    public static void reverse(char[] arr, int left, int right) {
        right--;
        while(left < right){
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    private static int maxElement(int[] arr, int index){
        if(index == arr.length) return arr[arr.length - 1];
        return Math.max(maxElement(arr,index + 1), arr[index]);
    }
    private static int maxElement(int[] arr, int low, int high){
        if(low == high) return arr[low];
        int mid = low + (high - low) / 2;
        int leftMax = maxElement(arr,low,mid);
        int rightMax = maxElement(arr,mid+1,high);
        return Math.max(leftMax,rightMax);
    }
    private static int sum(int[] arr, int index){
        if(index == arr.length - 1) return arr[index];
        return arr[index]+sum(arr,index + 1);
    }
    private static int sum(int[] arr, int low, int high){
        if(low == high) return arr[low];
        int mid = low + (high - low) / 2;
        int leftSum = sum(arr,low,mid);
        int rightSum = sum(arr,mid + 1, high);
        return leftSum+rightSum;
    }
    private static boolean contain(String str, int index, char c){
        if(index == str.length()) return false;
        if(str.charAt(index) == c) return true;
        return contain(str,index+1,c);
    }

    private static boolean contain(String str, int low, int high, char c){
        if(low > high) return false;
        if(low == high) return str.charAt(low) == c;
        int mid = low + (high - low) / 2;
        return contain(str,low, mid,c) || contain(str,mid+1,high,c);
    }

    private static boolean isPal(String str, int low, int high){
        if(low >= high) return true;
        if(str.charAt(low) != str.charAt(high)) return false;
        return isPal(str,low+1,high - 1);
    }
}
