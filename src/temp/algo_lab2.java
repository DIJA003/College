package temp;

public class algo_lab2 {
    public static void main(String[] args) {
        min_max(new int[]{50, 31, 2, 4, 5, 6, 7});
    }

    static void subset(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = 1; j < len - 1; j++) {
                for (int k = 2; k < len; k++) {
                    System.out.println("[" + arr[i] + ", " + arr[j] + ", " + arr[k] + "]");
                }
            }
        }
    }

    static void min_max(int[] arr) {
        int len = arr.length;
        int min, max;
        int i;
        if(len > 2){
            if(arr[0] > arr[1]){
                 max = arr[0];
                 min = arr[1];
            }
            else {
                 min = arr[0];
                 max = arr[1];
            }
            i=2;
        }
        else {
            min = max = arr[0];
            i = 1;
        }
        for (;i < len - 1; i += 2) {
            int large = arr[i], small = arr[i+1];
            if(large > small){

            }
            if (arr[i] >= arr[i + 1]) {
                if (arr[i + 1] < min) min = arr[i + 1];
                if (arr[i] > max) max = arr[i];
            }
            else
            {
                if(arr[i] < min) min = arr[i];
                if(arr[i+1] > max) max = arr[i];
            }
        }

        System.out.println(min + " " + max);
    }

}
