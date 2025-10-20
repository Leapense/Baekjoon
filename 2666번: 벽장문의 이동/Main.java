import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] use;
    static int[][][] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FastScanner fs = new FastScanner(br);

        N = fs.nextInt();
        int o1 = fs.nextInt();
        int o2 = fs.nextInt();
        if (o1 > o2) {
            int tmp = o1;
            o1 = o2;
            o2 = tmp;
        }

        M = fs.nextInt();
        use = new int[M];
        for (int i = 0; i < M; i++) use[i] = fs.nextInt();

        dp = new int[M + 1][N + 1][N + 1];
        for (int i = 0; i <= M; i++) {
            for (int a = 0; a <= N; a++) {
                Arrays.fill(dp[i][a], -1);
            }
        }

        int ans = solve(0, o1, o2);
        System.out.println(ans);
    }
    
    static int solve(int idx, int a, int b) {
        if (a > b) { int t = a; a = b; b = t; }
        if (idx == M) return 0;
        if (dp[idx][a][b] != -1) return dp[idx][a][b];

        int t = use[idx];
        if (t == a || t == b) {
            return dp[idx][a][b] = solve(idx + 1, a, b);
        }

        int moveLeft = Math.abs(t - a) + solve(idx + 1, t, b);
        int moveRight = Math.abs(t - b) + solve(idx + 1, a, t);
        return dp[idx][a][b] = Math.min(moveLeft, moveRight);
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(BufferedReader br) { this.br = br; }
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
}