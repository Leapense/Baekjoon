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
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1)
                    return Integer.MIN_VALUE;
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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int scenario = 1;
        while (true) {
            int n = fs.nextInt();
            int k = fs.nextInt();
            if (n == Integer.MIN_VALUE || k == Integer.MIN_VALUE)
                break;
            if (n == 0 && k == 0)
                break;

            int[][][] cost = new int[n + 1][n + 1][];
            int[][] period = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j)
                        continue;
                    int d = fs.nextInt();
                    period[i][j] = d;
                    int[] arr = new int[d];
                    for (int t = 0; t < d; t++)
                        arr[t] = fs.nextInt();
                    cost[i][j] = arr;
                }
            }

            final long INF = Long.MAX_VALUE / 4;
            long[] dp = new long[n + 1];
            Arrays.fill(dp, INF);
            dp[1] = 0;

            for (int day = 1; day <= k; day++) {
                long[] ndp = new long[n + 1];
                Arrays.fill(ndp, INF);

                for (int u = 1; u <= n; u++) {
                    if (dp[u] >= INF)
                        continue;

                    for (int v = 1; v <= n; v++) {
                        if (u == v)
                            continue;

                        int[] arr = cost[u][v];
                        int c = arr[(day - 1) % arr.length];
                        if (c == 0)
                            continue;

                        long nv = dp[u] + c;
                        if (nv < ndp[v])
                            ndp[v] = nv;
                    }
                }
                dp = ndp;
            }

            sb.append("Scenario #").append(scenario++).append('\n');
            if (dp[n] >= INF) {
                sb.append("No flight possible.\n\n");
            } else {
                sb.append("The best flight costs ").append(dp[n]).append(".\n\n");
            }
        }

        System.out.print(sb.toString());
    }
}