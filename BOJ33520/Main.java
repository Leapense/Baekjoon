import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

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

            long value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = (int) fs.nextLong();

        long maxSmall = 0;
        long maxLarge = 0;

        for (int i = 0; i < n; i++) {
            long a = fs.nextLong();
            long b = fs.nextLong();

            long small = Math.min(a, b);
            long large = Math.max(a, b);

            if (small > maxSmall) maxSmall = small;
            if (large > maxLarge) maxLarge = large;
        }

        System.out.print(maxSmall * maxLarge);
    }
}