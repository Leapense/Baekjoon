import java.io.*;
import java.util.*;

public class Main {
    static final int[] BOARD = {
        20, 1, 18, 4, 13, 6, 10, 15, 2, 17,
        3, 19, 7, 16, 8, 11, 14, 9, 12, 5
    };

    static int[] pos = new int[21];
    static int[] prefix = new int[21];

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            pos[BOARD[i]] = i;
        }

        for (int i = 0; i < 20; i++) {
            prefix[i + 1] = prefix[i] + BOARD[i];
        }

        int n = fs.nextInt();

        for (int i = 0; i < n; i++) {
            int w1 = fs.nextInt();
            int w2 = fs.nextInt();
            int w3 = fs.nextInt();

            int p0 = pos[w1];
            int p1 = pos[w2];
            int p2 = pos[w3];

            if (p0 > p1) {
                int tmp = p0; p0 = p1; p1 = tmp;
            }
            if (p1 > p2) {
                int tmp = p1; p1 = p2; p2 = tmp;
            }
            if (p0 > p1) {
                int tmp = p0; p0 = p1; p1 = tmp;
            }

            int answer;
            if (p0 == p2) {
                answer = BOARD[p0];
            } else {
                int s1 = rangeSum(p0, p1);
                int s2 = rangeSum(p1, p2);
                int s3 = circularSum(p2, p0);
                answer = Math.max(s1, Math.max(s2, s3));
            }
            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }

    static int rangeSum(int l, int r) {
        return prefix[r + 1] - prefix[l];
    }

    static int circularSum(int l, int r) {
        if (l <= r) {
            return rangeSum(l, r);
        }
        return rangeSum(l, 19) + rangeSum(0, r);
    }

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
}
