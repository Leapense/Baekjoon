import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final long MOD = (1L << 31) - 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) {
            return;
        }

        int n = Integer.parseInt(line.trim());
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            grid[i] = row.toCharArray();
        }

        boolean[][] possible = new boolean[n][n];
        long[][] ways = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '#') {
                    possible[i][j] = false;
                    ways[i][j] = 0;
                    continue;
                }

                if (i == 0 && j == 0) {
                    possible[i][j] = true;
                    ways[i][j] = 1;
                    continue;
                }

                boolean can = false;
                long cnt = 0L;

                if (i > 0 && possible[i - 1][j]) {
                    can = true;
                }
                if (j > 0 && possible[i][j - 1]) {
                    can = true;
                }
                if (i > 0) {
                    cnt += ways[i - 1][j];
                }
                if (j > 0) {
                    cnt += ways[i][j - 1];
                }
                possible[i][j] = can;
                ways[i][j] = cnt % MOD;
            }
        }
        if (possible[n - 1][n - 1]) {
            System.out.println(ways[n - 1][n - 1] % MOD);
            return;
        }

        boolean reachable = bfsReachable(grid, n);
        if (reachable) {
            System.out.println("THE GAME IS A LIE");
        } else {
            System.out.println("INCONCEIVABLE");
        }
    }

    private static boolean bfsReachable(char[][] grid, int n) {
        boolean[][] visited = new boolean[n][n];
        if (grid[0][0] == '#') {
            return false;
        }

        int[] q = new int[n * n];
        int head = 0;
        int tail = 0;

        visited[0][0] = true;
        q[tail++] = 0;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (head < tail) {
            int cur = q[head++];
            int r = cur / n;
            int c = cur % n;

            if (r == n - 1 && c == n - 1) {
                return true;
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }
                if (grid[nr][nc] == '#') {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                q[tail++] = nr * n + nc;
            }
        }

        return false;
    }
}