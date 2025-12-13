import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 5_000_011;
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int K = fs.nextInt();

        int size = K + 1;
        int[] ring = new int[size];
        ring[0] = 1;

        int prev = 1;

        for (int i = 1; i <= N; i++) {
            int idx = i - K - 1;
            int add;
            if (idx >= 0) {
                add = ring[idx % size];
            } else {
                add = 1;
            }

            int cur = prev + add;
            if (cur >= MOD) cur -= MOD;
            ring[i % size] = cur;
            prev = cur;
        }

        System.out.println(prev % MOD);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }
        
        private int readByte() throws IOException {
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
                c = readByte();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}