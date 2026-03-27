import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        long mask = 0;
        for (int i = 0; i <= N; i++) {
            int bit = fs.nextInt();
            mask = (mask << 1) | bit;
        }

        long count = 0;
        while (mask != 1) {
            if ((mask & 1L) == 1L) {
                mask = (mask << 1) ^ mask ^ 1L;
            } else {
                mask >>= 1;
            }
            count++;
        }

        System.out.print(count);
    }
}