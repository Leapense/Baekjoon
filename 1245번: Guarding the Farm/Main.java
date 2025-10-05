import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] h;
    static boolean[][] vis;
    static final int[] dx = {-1,-1,-1,0,0,1,1,1};
    static final int[] dy = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        h = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                h[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        vis = new boolean[N][M];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!vis[i][j]) {
                    if (bfsIsPeak(i, j)) answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static boolean bfsIsPeak(int sx, int sy) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        vis[sx][sy] = true;
        int target = h[sx][sy];
        boolean isPeak = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (h[nx][ny] > target) {
                    isPeak = false;
                }
                if (!vis[nx][ny] && h[nx][ny] == target) {
                    vis[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return isPeak;
    }
}