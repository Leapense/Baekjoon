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
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (true) {
            long[] a = new long[12];
            boolean eof = false;

            for (int i = 0; i < 12; i++) {
                long v = fs.nextLong();
                if (v == Long.MIN_VALUE) {
                    eof = true;
                    break;
                }
                a[i] = v;
            }

            if (eof) break;

            boolean allZero = true;
            for (int i = 0; i < 12; i++) {
                if (a[i] != 0) {
                    allZero = false;
                    break;
                }
            }

            if (allZero) break;

            Arrays.sort(a);

            boolean ok = true;
            for (int i = 0; i < 12; i += 4) {
                long v = a[i];
                if (a[i + 1] != v || a[i + 2] != v || a[i + 3] != v) {
                    ok = false;
                    break;
                }
            }

            sb.append(ok ? "yes" : "no").append('\n');
        }

        System.out.print(sb.toString());
    }
}