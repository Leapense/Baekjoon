import java.io.*;
import java.util.*;

public class Main {
    static char[] digits;
    static long[][] memo;
    static boolean[][] visited;
    static int n;
    static int fullMask;

    static long dfs(int mask, int mod) {
        if (mask == fullMask) {
            return mod == 0 ? 1L : 0L;
        }

        if (visited[mask][mod]) {
            return memo[mask][mod];
        }
        visited[mask][mod] = true;

        long result = 0L;

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) continue;

            // 중복 순열 제거
            if (i > 0 && digits[i] == digits[i - 1] && (mask & (1 << (i - 1))) == 0) {
                continue;
            }

            int digit = digits[i] - '0';
            int nextMask = mask | (1 << i);
            int nextMod = (mod * 10 + digit) % 7;

            result += dfs(nextMask, nextMod);
        }

        memo[mask][mod] = result;
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            String s = br.readLine().trim();
            digits = s.toCharArray();
            Arrays.sort(digits);

            n = digits.length;
            fullMask = (1 << n) - 1;

            memo = new long[1 << n][7];
            visited = new boolean[1 << n][7];

            long answer = dfs(0, 0);

            sb.append("Case ").append(tc).append(": ").append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }
}