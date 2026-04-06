import java.io.*;
import java.math.BigInteger;

public class Main {
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

        String next() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

    static int mod1000(long x) {
        int r = (int) (x % 1000);
        if (r < 0) r += 1000;
        return r;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int s = (int) fs.nextLong();
        long x = fs.nextLong();

        int xm = mod1000(x);
        int mod = 0;

        if (x == 0 || x == 1 || x == -1) {
            long val = 0;
            for (int i = 0; i < s; i++) {
                long c = fs.nextLong();
                val = val * x + c;
                mod = (mod * xm + mod1000(c)) % 1000;
            }

            int ans = (int) (Math.abs(val) % 1000);
            System.out.printf("%03d", ans);
            return;
        }

        long absX = Math.abs(x);
        long C = 1_000_000L;
        long T = C / (absX - 1) + 1;

        long val = 0;
        boolean saturated = false;
        int sign = 0;

        for (int i = 0; i < s; i++) {
            long c = fs.nextLong();
            mod = (mod * xm + mod1000(c)) % 1000;

            if (!saturated) {
                long next = val * x + c;

                if (Math.abs(next) >= T) {
                    saturated = true;
                    sign = Long.compare(next, 0);
                } else {
                    val = next;
                }
            } else {
                if (x < 0) sign = -sign;
            }
        }

        int finalSign = saturated ? sign : Long.compare(val, 0);
        int ans;
        if (finalSign >= 0) {
            ans = mod;
        } else {
            ans = (1000 - mod) % 1000;
        }

        System.out.printf("%03d", ans);
    }
}