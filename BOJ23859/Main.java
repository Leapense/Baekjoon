import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine().trim();
        int k = Integer.parseInt(br.readLine().trim());

        char[] a = s.toCharArray();
        int n = a.length;

        char first = 'z';
        for (char c : a) first = (char)Math.min(first, c);
        
        boolean[] cur = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (a[i] == first) cur[i] = true;
        }

        StringBuilder ans = new StringBuilder(k);
        ans.append(first);

        for (int step = 2; step <= k; step++) {
            boolean[] cand = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!cur[i]) continue;
                if (i > 0) cand[i - 1] = true;
                if (i + 1 < n) cand[i + 1] = true;
            }

            char best = 'z';
            for (int i = 0; i < n; i++) {
                if (cand[i] && a[i] < best) best = a[i];
            }

            ans.append(best);

            boolean[] next = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (cand[i] && a[i] == best) next[i] = true;
            }

            cur = next;
        }

        System.out.println(ans.toString());
    }
}