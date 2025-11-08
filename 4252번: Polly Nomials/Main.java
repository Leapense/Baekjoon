import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();
        int caseNo = 1;

        while (true) {
            if (!fs.hasNext()) break;
            int n = fs.nextInt();
            BigInteger second = fs.nextBigInteger();
            if (n == 0 && second.equals(BigInteger.ZERO)) break;

            BigInteger[] a = new BigInteger[n + 1];
            a[n] = second;
            for (int i = n - 1; i >= 0; --i) {
                a[i] = fs.nextBigInteger();
            }
            int x = fs.nextInt();

            BigInteger xv = BigInteger.valueOf(x);
            BigInteger val = a[n];
            for (int i = n - 1; i >= 0; --i) {
                val = val.multiply(xv).add(a[i]);
            }

            long cost = 2L * n;
            for (int i = 0; i <= n - 1; ++i) {
                if (a[i].signum() > 0) {
                    int digits = a[i].toString().length();
                    cost += 1 + digits;
                }
            }

            out.append("Polynomial ").append(caseNo).append(": ")
               .append(val.toString()).append(" ").append(cost).append('\n');
            caseNo++;
        }

        System.out.print(out.toString());
    }

    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        boolean hasNext() throws IOException {
            int c;
            while ((c = read()) != -1) {
                if (!Character.isWhitespace(c)) { ptr--; return true; }
            }

            return false;
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && Character.isWhitespace(c)) {}
            if (c == -1) return null;
            do {
                sb.append((char) c);
                c = read();
            } while (c != -1 && !Character.isWhitespace(c));
            return sb.toString();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
        BigInteger nextBigInteger() throws IOException { return new BigInteger(next()); }
    }
}