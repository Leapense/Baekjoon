import java.io.*;
import java.util.*;

public class Main {

    private static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0,
            len = 0;

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

            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = (int) fs.nextLong();
        long ans = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            long M = fs.nextLong();
            int O = (int) fs.nextLong();
            if (O == 0 && M < ans) ans = M;
        }

        if (ans == Long.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(ans);
        }
    }
}
