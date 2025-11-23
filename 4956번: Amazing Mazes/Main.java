import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            StringTokenizer st = new StringTokenizer(line);
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            boolean[][] hWalls = new boolean[h][w - 1];
            boolean[][] vWalls = new boolean[h - 1][w];

            for (int i = 0; i < 2 * h - 1; i++) {
                String wallLine = br.readLine();
                if (wallLine == null) {
                    wallLine = "";
                }
                StringTokenizer wst = new StringTokenizer(wallLine);

                if (i % 2 == 0) {
                    int row = i / 2;
                    for (int c = 0; c < w - 1; c++) {
                        if (!wst.hasMoreTokens()) {
                            break;
                        }
                        int val = Integer.parseInt(wst.nextToken());
                        hWalls[row][c] = (val == 1);
                    }
                } else {
                    int row = i / 2;
                    for (int c = 0; c < w; c++) {
                        if (!wst.hasMoreTokens()) {
                            break;
                        }

                        int val = Integer.parseInt(wst.nextToken());
                        vWalls[row][c] = (val == 1);
                    }
                }
            }

            int answer = bfs(w, h, hWalls, vWalls);
            sb.append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }

    private static int bfs(int w, int h, boolean[][] hWalls, boolean[][] vWalls) {
        int[][] dist = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(dist[i], -1);
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        dist[0][0] = 1;
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int d = dist[r][c];

            if (r == h - 1 && c == w - 1) {
                return d;
            }

            if (c > 0 && !hWalls[r][c - 1] && dist[r][c - 1] == -1) {
                dist[r][c - 1] = d + 1;
                q.offer(new int[]{r, c - 1});
            }
            if (c < w - 1 && !hWalls[r][c] && dist[r][c + 1] == -1) {
                dist[r][c + 1] = d + 1;
                q.offer(new int[]{r, c + 1});
            }
            if (r > 0 && !vWalls[r - 1][c] && dist[r - 1][c] == -1) {
                dist[r - 1][c] = d + 1;
                q.offer(new int[]{r - 1, c});
            }
            if (r < h - 1 && !vWalls[r][c] && dist[r + 1][c] == -1) {
                dist[r + 1][c] = d + 1;
                q.offer(new int[]{r + 1, c});
            }
        }

        return 0;
    }
}