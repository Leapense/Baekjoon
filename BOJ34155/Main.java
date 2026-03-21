import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 998244353L;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        long ans = 1L;

        for (int i = 1; i <= N; i++) {
            int ai = fs.nextInt();
            if (ai == i) {
                ans = (ans * (N - 1L)) % MOD;
            } else {
                ans = (ans * (N - 2L)) % MOD;
            }
        }

        System.out.print(ans);
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