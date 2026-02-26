import java.io.*;
import java.util.*;

public class Main {

    static class FastScanner {
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
                if (c == -1) break;
            }
            return sb.toString();
        }

        int nextInt() throws IOException {
            String s = next();
            if (s == null) throw new EOFException("Unexpected EOF");
            return Integer.parseInt(s);
        }
    }

    static int parseGradeTenths(String s) {
        int dot = s.indexOf('.');
        if (dot < 0) return Integer.parseInt(s) * 10;

        int whole = Integer.parseInt(s.substring(0, dot));
        int dec = 0;
        if (dot + 1 < s.length()) {
            char ch = s.charAt(dot + 1);
            if (ch >= '0' && ch <= '9') dec = ch - '0';
        }
        return whole * 10 + dec;
    }

    static long ceilDiv(long a, long b) {
        if (a >= 0) return (a + b - 1) / b;
        return a / b;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int wu = fs.nextInt();
        int n = fs.nextInt();
        if (wu <= 0) {
            System.out.print("IMPOSSIBLE");
            return;
        }

        long sumWeighted = 0;
        long sumWeights = 0;
        boolean impossible = false;

        for (int i = 0; i < n; i++) {
            String gStr = fs.next();
            int wi = fs.nextInt();
            if (gStr == null) throw new EOFException("Unexpected EOF");

            int gi10 = parseGradeTenths(gStr);
            if (gi10 < 58) impossible = true;

            sumWeights += wi;
            sumWeighted += (long) gi10 * wi;
        }

        if (impossible) {
            System.out.print("IMPOSSIBLE");
            return;
        }

        long totalWeight = sumWeights + wu;
        long need = 80L * totalWeight - sumWeighted;

        long requiredFromAvg10 = ceilDiv(need, wu);
        long required10 = Math.max(58L, requiredFromAvg10);

        if (required10 > 100L) {
            System.out.print("IMPOSSIBLE");
            return;
        }

        System.out.print((required10 / 10) + "." + (required10 % 10));
    }
}