package prep;

public class NQueenProblem {
    public static void main(String[] args){
        int n = 3;
//        int[] board = new int[n];
//
//        if (solveNQueen(board, 0, n)) {
//            System.out.println("solution for " + n + " queens:");
//            printQueens(board, n);
//        } else {
//            System.out.println("no solution for " + n);
//        }
        int[] col = new int [30];
        solveQ(col,n,0);
    }
    static boolean solveNQueen(int[] board, int r, int n){
        if(r == n) return true;

        for(int i = 0; i < n; i++){
            if(safe(board,r,i)){
                board[r] = i;
                if(solveNQueen(board,r + 1,n)) return true;
                board[r] = -1;
            }
        }
        return false;
    }
    static boolean safe(int[] board, int r, int c){
        for(int prev = 0; prev < r; ++prev){
            int pc = board[prev];
            if(pc == c) return false;
            if(Math.abs(prev - r) == Math.abs(pc - c)) return false;
        }
        return true;
    }
    static void printQueens(int[] board, int n){
        for(int i = 0; i < n ;i++){
            for(int j = 0; j < n; ++j) {
                if (board[i] == j) System.out.print("Q ");
                else System.out.print(". ");
            }
            System.out.println();
        }
    }


    static void solveQ(int[]col, int n, int i){
        if(promising(col,i)){
            if(i == n){
                for(int r = 1; r <= n; r++){
                    System.out.print(col[r] + " ");
                }
                System.out.println();
            } else{
                for(int j = 1; j <= n; ++j){
                    col[i + 1] = j;
                    solveQ(col,n,i + 1);
                }
            }
        }
    }
    static boolean promising(int[] col,int i){
        int k = 1;
        boolean flag = true;
        while(k < i && flag){
            if(col[i] == col[k] || Math.abs(col[i] - col[k]) == (i-k)) flag = false;
            k++;
        }
        return flag;
    }
}
