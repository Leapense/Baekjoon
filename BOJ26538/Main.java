import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            this.in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();

        while (t-- > 0) {
            int w = fs.nextInt();
            int h = fs.nextInt();
            int q = fs.nextInt();

            int[][] diff = new int[h][w + 1];
            boolean[][] scare = new boolean[h][w];

            for (int i = 0; i < q; i++) {
                int r = fs.nextInt();
                int c = fs.nextInt();
                int p = fs.nextInt();

                if (r >= 0 && r < h && c >= 0 && c < w) {
                    scare[r][c] = true;
                }

                int rowStart = Math.max(0, r - p);
                int rowEnd = Math.min(h - 1, r + p);

                for (int row = rowStart; row <= rowEnd; row++) {
                    int remaining = p - Math.abs(row - r);
                    int colStart = Math.max(0, c - remaining);
                    int colEnd = Math.min(w - 1, c + remaining);

                    if (colStart <= colEnd) {
                        diff[row][colStart]++;
                        diff[row][colEnd + 1]--;
                    }
                }
            }

            for (int row = 0; row < h; row++) {
                int active = 0;
                for (int col = 0; col < w; col++) {
                    active += diff[row][col];
                    if (scare[row][col]) {
                        out.append('x');
                    } else if (active > 0) {
                        out.append('*');
                    } else {
                        out.append('.');
                    }
                }

                out.append('\n');
            }

            if (t > 0) out.append('\n');
        }

        System.out.print(out);
    }
}