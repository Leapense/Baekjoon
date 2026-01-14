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

        int nextInt() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return Integer.MIN_VALUE;
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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int l = fs.nextInt();
        int a = fs.nextInt();

        int prev = fs.nextInt();
        boolean possible = true;

        for (int i = 0; i < l; i++) {
            int cur = fs.nextInt();
            int diff = cur - prev;
            if (diff > a) {
                possible = false;

                System.out.println("BUG REPORT");
                return;
            }

            prev = cur;
        }

        System.out.println("POSSIBLE");
    }
}