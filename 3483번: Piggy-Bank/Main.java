import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
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

        int nextInt() throws IOException {
            int c, sgn = 1, x = 0;
            do { c = read(); } while (c <= ' ' && c != -1);
            if (c == '-') { sgn = -1; c = read(); }
            for (; c > ' '; c = read()) x = x * 10 + (c - '0');
            return x * sgn;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();
        final int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int E = fs.nextInt();
            int F = fs.nextInt();
            int N = fs.nextInt();
            int M = F - E;

            int[] P = new int[N];
            int[] W = new int[N];
            for (int i = 0; i < N; i++) {
                P[i] = fs.nextInt();
                W[i] = fs.nextInt();
            }

            final int INF = 1_000_000_000;
            int[] dp = new int[M + 1];
            Arrays.fill(dp, INF);
            dp[0] = 0;

            for (int i = 0; i < N; i++) {
                int wi = W[i], pi = P[i];
                for (int w = wi; w <= M; w++) {
                    if (dp[w - wi] + pi < dp[w]) {
                        dp[w] = dp[w - wi] + pi;
                    }
                }
            }

            if (dp[M] >= INF) {
                sb.append("This is impossible.\n");
            } else {
                sb.append("The minimum amount of money in the piggy-bank is ")
                  .append(dp[M]).append(".\n");
            }
        }

        System.out.print(sb.toString());
    }
}