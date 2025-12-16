import java.io.*;
import java.util.*;

public class Main {
    static List<int[]> solutions = new ArrayList<>(92);
    static boolean[] colUsed = new boolean[8];
    static boolean[] diag1 = new boolean[15];
    static boolean[] diag2 = new boolean[15];

    static void generate(int row, int[] pos) {
        if (row == 8) {
            solutions.add(pos.clone());
            return;
        }

        for (int col = 0; col < 8; col++) {
            int d1 = row + col;
            int d2 = row - col + 7;
            if (colUsed[col] || diag1[d1] || diag2[d2])
                continue;
            colUsed[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            pos[row] = col;

            generate(row + 1, pos);
            colUsed[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    static String rightJustify5(int value) {
        String s = String.valueOf(value);
        StringBuilder sb = new StringBuilder(6);
        for (int i = s.length(); i < 5; i++)
            sb.append(' ');
        sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        generate(0, new int[8]);

        int k = fs.nextInt();
        StringBuilder out = new StringBuilder();

        for (int b = 0; b < k; b++) {
            int[][] board = new int[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j] = fs.nextInt();
                }
            }

            int best = 0;
            for (int[] sol : solutions) {
                int sum = 0;
                for (int row = 0; row < 8; row++) {
                    sum += board[row][sol[row]];
                }
                if (sum > best)
                    best = sum;
            }

            out.append(rightJustify5(best)).append('\n');
        }

        System.out.print(out);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val;
        }
    }
}