import java.io.*;
import java.util.*;

public class Main {
    private static class FastScanner {
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

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return Long.MIN_VALUE;
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        char nextNonSpaceChar() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return 0;
            } while (c <= ' ');
            return (char) c;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long x = fs.nextLong();
        long b = fs.nextLong();

        char firstSmall = fs.nextNonSpaceChar();

        long cycle = 3L * b;
        long pairs = x / cycle;
        long r = x % cycle;
        
        long ans = 2L * pairs;
        if (r == 0) {
            System.out.print(ans);
            return;
        }

        long a1 = (firstSmall == 'L') ? b : 2L * b;

        if (r <= a1) ans += 1;
        else ans += 2;

        System.out.print(ans);
    }
}