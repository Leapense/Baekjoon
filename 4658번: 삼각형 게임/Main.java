import java.io.*;
import java.util.*;

public class Main {
    static int[][] tri = new int[6][3];
    static boolean[] used = new boolean[6];
    static int[] left = new int[6];
    static int[] right = new int[6];
    static int[] outer = new int[6];
    static int best;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        int count = 0;
        int[][] current = new int[6][3];

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals("$")) {
                if (count == 6) {
                    int result = solve(current);
                    if (result < 0) sb.append("none\n");
                    else sb.append(result).append('\n');
                }
                break;
            }

            if (line.equals("*")) {
                if (count == 6) {
                    int result = solve(current);
                    if (result < 0) sb.append("none\n");
                    else sb.append(result).append('\n');
                }
                count = 0;
                current = new int[6][3];
                continue;
            }
            StringTokenizer st = new StringTokenizer(line);
            for (int j = 0; j < 3; j++) {
                if (!st.hasMoreTokens()) return;
                current[count][j] = Integer.parseInt(st.nextToken());
            }
            count++;
        }

        System.out.println(sb.toString());
    }

    static int solve(int[][] t) {
        for (int i = 0; i < 6; i++) {
            System.arraycopy(t[i], 0, tri[i], 0, 3);
        }
        Arrays.fill(used, false);
        best = -1;
        dfs(0, 0);
        return best;
    }

    static void dfs(int pos, int currSum) {
        if (pos == 6) {
            if (right[5] == left[0]) {
                if (currSum > best) best = currSum;
            }
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (used[i]) continue;
            used[i] = true;

            for (int rot = 0; rot < 3; rot++) {
                int l = tri[i][(0 + rot) % 3];
                int o = tri[i][(1 + rot) % 3];
                int r = tri[i][(2 + rot) % 3];
                if (pos > 0 && l != right[pos - 1]) continue;

                left[pos] = l;
                outer[pos] = o;
                right[pos] = r;

                dfs(pos + 1, currSum + o);
            }
            used[i] = false;
        }
    }
}