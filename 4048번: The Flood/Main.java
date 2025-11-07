import java.io.*;
import java.util.*;

public class Main {
    private static final class Point {
        final int r;
        final int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var sb = new StringBuilder();
        int caseNum = 1;

        while (true) {
            var st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            int[][] grid = new int[n][m];
            var heights = new HashSet<Integer>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if (grid[i][j] > 0) {
                        heights.add(grid[i][j]);
                    }
                }
            }

            var sortedHeights = new ArrayList<>(heights);
            Collections.sort(sortedHeights);

            int splitLevel = -1;

            for (int f : sortedHeights) {
                boolean[][] isFlooded = new boolean[n][m];
                var qFlood = new LinkedList<Point>();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                            if (grid[i][j] <= f && !isFlooded[i][j]) {
                                isFlooded[i][j] = true;
                                qFlood.add(new Point(i, j));
                            }
                        }
                    }
                }

                while (!qFlood.isEmpty()) {
                    var curr = qFlood.poll();
                    for (int k = 0; k < 4; k++) {
                        int nr = curr.r + dr[k];
                        int nc = curr.c + dc[k];

                        if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                            if (!isFlooded[nr][nc] && grid[nr][nc] <= f) {
                                isFlooded[nr][nc] = true;
                                qFlood.add(new Point(nr, nc));
                            }
                        }
                    }
                }

                boolean[][] visitedLand = new boolean[n][m];
                int firstLandR = -1;
                int firstLandC = -1;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (!isFlooded[i][j]) {
                            firstLandR = i;
                            firstLandC = j;
                            break;
                        }
                    }
                    if (firstLandR != -1) break;
                }

                if (firstLandR == -1) {
                    continue;
                }

                var qLand = new LinkedList<Point>();
                qLand.add(new Point(firstLandR, firstLandC));
                visitedLand[firstLandR][firstLandC] = true;

                while (!qLand.isEmpty()) {
                    var curr = qLand.poll();

                    for (int k = 0; k < 4; k++) {
                        int nr = curr.r + dr[k];
                        int nc = curr.c + dc[k];

                        if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                            if (!isFlooded[nr][nc] && !visitedLand[nr][nc]) {
                                visitedLand[nr][nc] = true;
                                qLand.add(new Point(nr, nc));
                            }
                        }
                    }
                }

                boolean islandSplit = false;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (!isFlooded[i][j] && !visitedLand[i][j]) {
                            islandSplit = true;
                            break;
                        }
                    }
                    if (islandSplit) break;
                }

                if (islandSplit) {
                    splitLevel = f;
                    break;
                }
            }

            if (splitLevel == -1) {
                sb.append(String.format("Case %d: Island never splits.\n", caseNum));
            } else {
                sb.append(String.format("Case %d: Island splits when ocean rises %d feet.\n", caseNum, splitLevel));
            }

            caseNum++;
        }

        System.out.print(sb.toString());
    }
}