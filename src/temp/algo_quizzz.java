package temp;

public class algo_quizzz {
    public static void main(String[] args) {

    }
    //get most occurred character in a string
    private static char max_occur(String s) {
        int[] freq = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        int maxIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > freq[maxIndex]) maxIndex = i;
        }
        return (char) ('a'+maxIndex);

    }
    // merge to sorted arrays in linear time
    private static int[] combine(int[] arr1, int[] arr2){
        int leftSize = arr1.length, rightSize = arr2.length;
        int[] ans = new int [leftSize+rightSize];
        int i = 0, k = 0, j = 0;
        while(i < leftSize && j < rightSize){
            if(arr1[i] <= arr2[j]){
                ans[k] = arr1[i++];
            }
            else {
                ans[k] = arr2[j];
            }
            k++;
        }
        while (i < leftSize){
            ans[k++] = arr1[i++];
        }
        while (j < leftSize){
            ans[k++] = arr1[j++];
        }
        return ans;
    }
}
