import java.io.*;
import java.util.*;

public class Main {
    private static final int[] DR = {+1, +1, 0, -1, -1, -1, 0, +1};
    private static final int[] DC = {0, -1, -1, -1, 0, +1, +1, +1};

    private static final int[][] LETTER_DIRS = {
        {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8},
        {2, 3}, {2, 4}, {5, 7}, {2, 5}, {2, 6}, {2, 7}, {2, 8},
        {3, 4}, {3, 5}, {3, 6}, {3, 7}, {3, 8},
        {4, 5}, {4, 6}, {5, 8},
        {6, 7}, {6, 8}, {4, 7}, {7, 8}
    };

    private static final int[][] DIRS_TO_LETTER = new int[9][9];

    static {
        for (int i = 0; i < 9; i++) Arrays.fill(DIRS_TO_LETTER[i], -1);
        for (int i = 0; i < 26; i++) {
            int a = LETTER_DIRS[i][0];
            int b = LETTER_DIRS[i][1];
            DIRS_TO_LETTER[a][b] = i;
            DIRS_TO_LETTER[b][a] = i;
        }
    }

    private static int decodeLetter(char[][] g) {
        int sr = -1, sc = -1;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (g[r][c] == '*') {
                    sr = r;
                    sc = c;
                    break;
                }
            }

            if (sr != -1) break;
        }

        int d1 = -1, d2 = -1;
        for (int k = 0; k < 8; k++) {
            int nr = sr + DR[k];
            int nc = sc + DC[k];
            if (nr >= 0 && nr < 9 && nc >= 0 && nc < 9 && g[nr][nc] == '#') {
                if (d1 == -1) d1 = k + 1;
                else d2 = k + 1;
            }
        }

        return DIRS_TO_LETTER[d1][d2];
    }

    private static void drawLimb(char[][] out, int dir, char symbol) {
        int k = dir - 1;
        for (int step = 1; step <= 3; step++) {
            int r = 4 + DR[k] * step;
            int c = 4 + DC[k] * step;
            out[r][c] = symbol;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] g = new char[9][9];
        char[][] out = new char[9][9];
        for (int r = 0; r < 9; r++) Arrays.fill(out[r], '.');
        out[4][4] = '*';

        StringBuilder sb = new StringBuilder(N * 90);

        for (int i = 0; i < N; i++) {
            for (int r = 0; r < 9; r++) {
                String line = br.readLine();
                line.getChars(0, 9, g[r], 0);
            }

            int plain = decodeLetter(g);
            int cipher = (plain + C) % 26;

            int dirA = LETTER_DIRS[cipher][0];
            int dirB = LETTER_DIRS[cipher][1];

            drawLimb(out, dirA, '#');
            drawLimb(out, dirB, '#');

            for (int r = 0; r < 9; r++) {
                sb.append(out[r]).append('\n');
            }

            drawLimb(out, dirA, '.');
            drawLimb(out, dirB, '.');
        }

        System.out.print(sb.toString());
    }
}