import java.io.*;
import java.util.*;

public class Main {
    static int[][] buildAdj() {
        int n = 9;
        int[][] adj = new int[n][];
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int i = 0; i < n; i++) {
            int r = i / 3;
            int c = i % 3;

            int[] buf = new int[4];
            int cnt = 0;
            
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < 3 && 0 <= nc && nc < 3) {
                    buf[cnt++] = nr * 3 + nc;
                }
            }

            adj[i] = Arrays.copyOf(buf, cnt);
        }

        return adj;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine().trim());
        int start = K - 1;

        int sr = start / 3;
        int sc = start % 3;


        if (((sr + sc) & 1) == 1) {
            System.out.println(0);
            return;
        }

        int[][] adj = buildAdj();
        int n = 9;
        int full = (1 << n) - 1;

        long[][] dp = new long[1 << n][n];
        dp[1 << start][start] = 1;

        for (int mask = 0; mask <= full; mask++) {
            for (int v = 0; v < n; v++) {
                long cur = dp[mask][v];
                if (cur == 0) continue;

                for (int u : adj[v]) {
                    if ((mask & (1 << u)) != 0) continue;
                    dp[mask | (1 << u)][u] += cur;
                }
            }
        }

        long ans = 0;
        for (int v = 0; v < n; v++) ans += dp[full][v];

        System.out.println(ans);
    }
}
