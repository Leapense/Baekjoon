import java.io.*;

public class Main {
    public static void main(String ... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int n = s.length();

        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                dp[i + 1] += dp[i];
            }

            if (i + 1 < n) {
                int val = (c - '0') * 10 + (s.charAt(i + 1) - '0');
                if (val >= 10 && val <= 34) {
                    dp[i + 2] += dp[i];
                }
            }
        }

        System.out.println(dp[n]);
    }
}