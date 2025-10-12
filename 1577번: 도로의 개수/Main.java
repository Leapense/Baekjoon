import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }

            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        Integer nObj = null, mObj = null;
        String tok;
        tok = fs.next(); nObj = Integer.parseInt(tok);
        tok = fs.next(); mObj = Integer.parseInt(tok);
        int N = nObj, M = mObj;
        int K = fs.nextInt();

        boolean[][] blockedRight = new boolean[N + 1][M + 1];
        boolean[][] blockedUp = new boolean[N + 1][M + 1];
        for (int i = 0; i < K; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int c = fs.nextInt();
            int d = fs.nextInt();

            if (a == c) {
                int y = Math.min(b, d);
                if (y >= 0 && y < M) blockedUp[a][y] = true;
            } else if (b == d) {
                int x = Math.min(a, c);
                if (x >= 0 && x < N) blockedRight[x][b] = true;
            }
        }

        long[][] dp = new long[N + 1][M + 1];
        dp[0][0] = 1L;
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= M; y++) {
                if (x == 0 && y == 0) continue;
                long ways = 0L;
                if (x > 0 && !blockedRight[x - 1][y]) {
                    ways += dp[x - 1][y];
                }
                if (y > 0 && !blockedUp[x][y - 1]) {
                    ways += dp[x][y - 1];
                }
                dp[x][y] = ways;
            }
        }

        System.out.println(dp[N][M]);
    }
}