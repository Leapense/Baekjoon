import java.io.*;
import java.util.*;

public class Main {
    static final int N = 12;
    static final int[][] POS = {
        {0,4},
        {1,1},{1,3},{1,5},{1,7},
        {2,2},{2,6},
        {3,1},{3,3},{3,5},{3,7},
        {4,4}
    };
    static final int[][] LINES = {
        {0, 2, 5, 7},
        {0, 3, 6, 10},
        {7, 8, 9, 10},
        {1, 2, 3, 4},
        {1, 5, 8, 11},
        {4, 6, 9, 11}
    };

    static char[][] board = new char[5][9];
    static int[] arr = new int[N];
    static boolean[] used = new boolean[13];
    static boolean solved = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            board[i] = s.toCharArray();
        }

        for (int idx = 0; idx < N; idx++) {
            int r = POS[idx][0], c = POS[idx][1];
            char ch = board[r][c];
            if (ch == 'x') {
                arr[idx] = 0;
            } else {
                int val = ch - 'A' + 1;
                arr[idx] = val;
                used[val] = true;
            }
        }
        dfs(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(board[i]).append('\n');
        }

        System.out.print(sb.toString());
    }

    static void dfs(int idx) {
        if (solved) return;
        if (idx == N) {
            if (checkAllLines()) {
                for (int i = 0; i < N; i++) {
                    int r = POS[i][0], c = POS[i][1];
                    board[r][c] = (char)('A' + arr[i] - 1);
                }
                solved = true;
            }

            return;
        }

        if (arr[idx] != 0) {
            if (!partialOK()) return;
            dfs(idx + 1);
            return;
        }

        for (int v = 1; v <= 12; v++) {
            if (used[v]) continue;
            arr[idx] = v;
            used[v] = true;

            if (partialOK()) {
                dfs(idx + 1);
            }

            if (solved) return;
            used[v] = false;
            arr[idx] = 0;
        }
    }

    static boolean checkAllLines() {
        for (int[] line : LINES) {
            int s = 0;
            for (int p : line) s += arr[p];
            if (s != 26) return false;
        }

        return true;
    }

    static boolean partialOK() {
        for (int[] line : LINES) {
            int sum = 0, unk = 0;
            for (int p : line) {
                if (arr[p] == 0) unk++;
                else sum += arr[p];
            }

            if (unk == 0) {
                if (sum != 26) return false;
            } else {
                if (sum >= 26) return false;
                if (sum + unk > 26) return false;
                if (sum + 12 * unk < 26) return false;
            }
        }

        return true;
    }
}