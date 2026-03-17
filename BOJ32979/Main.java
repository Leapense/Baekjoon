import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        int T = fs.nextInt();
        int M = 2 * N;

        int[] a = new int[M];
        for (int i = 0; i < M; i++) {
            a[i] = fs.nextInt();
        }

        StringBuilder sb = new StringBuilder();
        int pos = 0;

        for (int i = 0; i < T; i++) {
            int b = fs.nextInt();
            pos = (pos + (b - 1)) % M;
            if (i > 0) sb.append(' ');
            sb.append(a[pos]);
        }

        System.out.print(sb);
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