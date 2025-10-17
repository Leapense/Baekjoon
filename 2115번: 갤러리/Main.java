import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] g = new char[M][N];
        for (int i = 0; i < M; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < N; j++) g[i][j] = s.charAt(j);
        }
        long ans = 0;
        for (int i = 0; i < M - 1; i++) {
            int c = 0;
            while (c < N) {
                if (g[i][c] != g[i + 1][c]) {
                    boolean wallAbove = (g[i][c] == 'X');
                    int len = 0;
                    int cc = c;
                    while (cc < N && g[i][cc] != g[i + 1][cc] && (g[i][cc] == 'X') == wallAbove) {
                        len++;
                        cc++;
                    }
                    ans += len / 2;
                    c = cc;
                } else {
                    c++;
                }
            }
        }

        for (int j = 0; j < N - 1; j++) {
            int r = 0;
            while (r < M) {
                if (g[r][j] != g[r][j + 1]) {
                    boolean wallLeft = (g[r][j] == 'X');
                    int len = 0;
                    int rr = r;
                    while (rr < M && g[rr][j] != g[rr][j + 1] && (g[rr][j] == 'X') == wallLeft) {
                        len++;
                        rr++;
                    }
                    ans += len / 2;
                    r = rr;
                } else {
                    r++;
                }
            }
        }

        System.out.println(ans);
    }
}