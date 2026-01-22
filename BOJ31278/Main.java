import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] ans = new int[n];

        for (int idx = 0; idx < n; idx++) {
            String s = br.readLine().trim();
            int m = s.length();
            boolean[] forbidden = new boolean[m];

            for (int i = 0; i < m; i++) {
                char c = s.charAt(i);

                if (c == 'D') {
                    forbidden[i] = true;
                } else if (c == 'B') {
                    int from = Math.max(0, i - 2);
                    for (int k = from; k <= i; k++)
                        forbidden[k] = true;
                } else if (c == 'S') {
                    int from = Math.max(0, i - 1);
                    int to = Math.min(m - 1, i + 1);
                    for (int k = from; k <= to; k++)
                        forbidden[k] = true;
                }
            }
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) == '-' && !forbidden[i])
                    count++;
            }
            ans[idx] = count;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(ans[i]);
        }

        System.out.print(sb.toString());
    }
}