import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static final int[] DR = {1, -1, 0, 0, 1, -1};
    static final int[] DC = {0, 0, 1, -1, -1, 1};

    static int n;
    static char[][] board;
    static boolean blackWins() {
        boolean[][] visited = new boolean[n][n];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int c = 0; c < n; c++) {
            if (board[0][c] == 'B') {
                visited[0][c] = true;
                q.add(new int[]{0, c});
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if (r == n - 1) {
                return true;
            }

            for (int d = 0; d < 6; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (!visited[nr][nc] && board[nr][nc] == 'B') {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return false;
    }

    static boolean whiteWins() {
        boolean[][] visited = new boolean[n][n];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            if (board[r][0] == 'W') {
                visited[r][0] = true;
                q.add(new int[]{r, 0});
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if (c == n - 1) {
                return true;
            }

            for (int d = 0; d < 6; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    if (!visited[nr][nc] && board[nr][nc] == 'W') {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;

        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            do {
                line = br.readLine();
            } while (line != null && line.trim().isEmpty());

            if (line == null) break;

            n = Integer.parseInt(line.trim());
            board = new char[n][n];
            for (int i = 0; i < n; i++) {
                String row = br.readLine();
                while (row != null && row.trim().isEmpty()) {
                    row = br.readLine();
                }

                board[i] = row.trim().toCharArray();
            }

            boolean b = blackWins();
            boolean w = whiteWins();

            if (b && !w) {
                sb.append("Black wins\n");
            } else if (!b && w) {
                sb.append("White wins\n");
            } else {
                sb.append("Not finished\n");
            }
        }

        System.out.print(sb.toString());
    }
}