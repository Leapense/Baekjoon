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
        long[] x = new long[3];
        x[0] = fs.nextLong();
        x[1] = fs.nextLong();
        x[2] = fs.nextLong();

        long S = x[0] + x[1] + x[2];
        long H = S / 2;

        boolean noCut = (x[0] == H) || (x[1] == H) || (x[2] == H) || (x[0] + x[1] == H) || (x[0] + x[2] == H) || (x[1] + x[2] == H);

        if (noCut) {
            System.out.print("0");
            return;
        }

        for (int i = 0; i < 3; i++) {
            long xi = x[i];
            long y = x[(i + 1) % 3];
            long z = x[(i + 2) % 3];

            long a = H - y;
            if (a > 0 && a < xi) {
                System.out.println(i + 1);
                System.out.println(a + " " + (xi - a));
                return;
            }

            a = H - z;
            if (a > 0 && a < xi) {
                System.out.println(i + 1);
                System.out.println(a + " " + (xi - a));
                return;
            }

            a = H;
            if (a > 0 && a < xi) {
                System.out.println(i + 1);
                System.out.println(a + " " + (xi - a));
                return;
            }
        }

        System.out.print("-1");
    }

}