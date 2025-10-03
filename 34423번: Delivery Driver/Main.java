import java.io.*;
import java.util.*;

public class Main {
    static final class FastScanner {
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
        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);
            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }

        int nextInt() throws IOException { return (int) nextLong(); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long tDF = fs.nextLong();
        long tDC = fs.nextLong();
        long tCF = fs.nextLong();

        int N = fs.nextInt();
        long[][] profit = new long[3][N];
        for (int c = 0; c < 3; c++) {
            for (int d = 0; d < N; d++) {
                profit[c][d] = fs.nextLong();
            }
        }

        long[][] T = new long[3][3];
        T[0][1] = T[1][0] = tDF;
        T[0][2] = T[2][0] = tDC;
        T[1][2] = T[2][1] = tCF;

        long[] prev = new long[3];
        for (int c = 0; c < 3; c++) prev[c] = profit[c][0];

        long[] cur = new long[3];
        for (int day = 1; day < N; day++) {
            for (int to = 0; to < 3; to++) {
                long best = Long.MIN_VALUE / 4;
                for (int from = 0; from < 3; from++) {
                    long cand = prev[from] - T[from][to];
                    if (cand > best) best = cand;
                }
                cur[to] = profit[to][day] + best;
            }

            long[] tmp = prev;
            prev = cur;
            cur = tmp;
        }

        long ans = Math.max(prev[0], Math.max(prev[1], prev[2]));
        System.out.println(ans);
    }
}