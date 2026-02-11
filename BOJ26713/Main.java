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

    private static int solveForPattern(long[] a, boolean startUp) {
        int n = a.length - 1;

        int dp0 = 0;
        int dp1 = 1;

        for (int i = 2; i <= n; i++) {
            boolean needUp;
            if (startUp) {
                needUp = (i % 2 == 0);
            } else {
                needUp = (i % 2 != 0);
            }

            boolean bad;
            if (needUp) {
                bad = !(a[i] > a[i - 1]);
            } else {
                bad = !(a[i] < a[i - 1]);
            }

            int newDp0;
            if (bad) {
                newDp0 = dp1;
            } else {
                newDp0 = Math.min(dp0, dp1);
            }
            int newDp1 = 1 + Math.min(dp0, dp1);

            dp0 = newDp0;
            dp1 = newDp1;
        }

        return Math.min(dp0, dp1);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long nVal = fs.nextLong();
        if (nVal == Long.MIN_VALUE) return;
        int n = (int) nVal;

        long[] a = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = fs.nextLong();
        }

        int ans1 = solveForPattern(a, true);
        int ans2 = solveForPattern(a, false);

        System.out.println(Math.min(ans1, ans2));
    }
}