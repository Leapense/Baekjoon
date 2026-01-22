import java.io.*;
import java.math.BigInteger;

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
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1)
                    return Long.MIN_VALUE;
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

    private static int compareWithDiff(long diff, long b, long d) {
        long diffSq = diff * diff;
        if (diffSq > d)
            return 1;
        if (diffSq == d) {
            return (b == 0) ? 0 : 1;
        }

        long sLong = d + diffSq - b;
        if (sLong <= 0) {
            return 1;
        }

        BigInteger S = BigInteger.valueOf(sLong);
        BigInteger left = S.multiply(S);
        BigInteger right = BigInteger.valueOf(4)
                .multiply(BigInteger.valueOf(diffSq))
                .multiply(BigInteger.valueOf(d));
        int cmp = left.compareTo(right);
        if (cmp < 0)
            return 1;
        if (cmp == 0)
            return 0;
        return -1;
    }

    private static int compare(long A, long B, long C, long D) {
        if (A == C) {
            return Long.compare(B, D);
        }

        if (A > C) {
            long diff = A - C;
            return compareWithDiff(diff, B, D);
        } else {
            long diff = C - A;
            int t = compareWithDiff(diff, D, B);
            return -t;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        long nVal = fs.nextLong();
        int N = (int) nVal;

        StringBuilder sb = new StringBuilder(N * 8);
        for (int i = 0; i < N; i++) {
            long A = fs.nextLong();
            long B = fs.nextLong();
            long C = fs.nextLong();
            long D = fs.nextLong();

            int res = compare(A, B, C, D);
            if (res < 0)
                sb.append("Less\n");
            else if (res == 0)
                sb.append("Equal\n");
            else
                sb.append("Greater\n");
        }

        System.out.print(sb.toString());
    }
}