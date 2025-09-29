import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while (true) {
                c = read();
                if (c == -1) return sb.length() == 0 ? null : sb.toString();
                if (!isSpace(c)) break;
            }

            while (c != -1 && !isSpace(c)) {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            int c;
            while (true) {
                c = read();
                if (c == -1) return Integer.MIN_VALUE;
                if (!isSpace(c)) break;
            }

            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }

            int val = 0;
            while (c >= '0' && c <= '9') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sgn;
        }

        private boolean isSpace(int c) {
            return c <= 32;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int U = fs.nextInt();
        int half = N / 2;

        boolean[][] grid = new boolean[N][N];
        int[][] cnt = new int[half][half];

        for (int r = 0; r < N; r++) {
            String line = fs.next();
            for (int c = 0; c < N; c++) {
                char ch = line.charAt(c);
                boolean on = (ch == '#');
                grid[r][c] = on;
                if (on) {
                    int rr = Math.min(r, N - 1 - r);
                    int cc = Math.min(c, N - 1 - c);
                    cnt[rr][cc]++;
                }
            }
        }

        int[] cost = {0, 1, 2, 1, 0};
        long ans = 0;
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                ans += cost[cnt[i][j]];
            }
        }

        StringBuilder out = new StringBuilder();
        out.append(ans).append('\n');

        for (int q = 0; q < U; q++) {
            int r = fs.nextInt() - 1;
            int c = fs.nextInt() - 1;

            int rr = Math.min(r, N - 1 - r);
            int cc = Math.min(c, N - 1 - c);

            int before = cnt[rr][cc];
            ans -= cost[before];

            if (grid[r][c]) {
                grid[r][c] = false;
                cnt[rr][cc] = before - 1;
            } else {
                grid[r][c] = true;
                cnt[rr][cc] = before + 1;
            }

            ans += cost[cnt[rr][cc]];
            out.append(ans).append('\n');
        }

        System.out.print(out.toString());
    }
}