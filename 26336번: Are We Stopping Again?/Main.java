import java.io.*;
import java.util.*;

public class Main {
    private static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }

        return a;
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = fs.nextInt();
        for (int i = 0; i < t; i++) {
            long D = fs.nextLong();
            long G = fs.nextLong();
            long F = fs.nextLong();

            sb.append(D).append(' ').append(G).append(' ').append(F).append('\n');
            long M = D - 1;
            long L = lcm(G, F);
            long stops = (M / G) + (M / F) - (M / L);
            sb.append(stops).append('\n');
        }

        System.out.print(sb.toString());
    }

    private static class FastScanner {
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
                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}