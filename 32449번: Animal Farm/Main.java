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

        String next() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return null;
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = readByte();
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

    private static int lowerBound(long[] arr, long target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = Integer.parseInt(fs.next());

        long[] pigsTmp = new long[n];
        long[] othersTmp = new long[n];

        int pc = 0, oc = 0;

        for (int i = 0; i < n; i++) {
            String species = fs.next();
            int influence = fs.nextInt();
            if ("pig".equals(species)) pigsTmp[pc++] = influence;
            else othersTmp[oc++] = influence;
        }

        long[] pigs = Arrays.copyOf(pigsTmp, pc);
        long[] others = Arrays.copyOf(othersTmp, oc);

        Arrays.sort(others);
        long[] pref = new long[others.length + 1];
        for (int i = 0; i < others.length; i++) {
            pref[i + 1] = pref[i] + others[i];
        }

        long ans = 0;
        for (long p : pigs) {
            int idx = lowerBound(others, p);
            long sumLess = pref[idx];
            ans = Math.max(ans, p + sumLess);
        }

        System.out.print(ans);
    }
}