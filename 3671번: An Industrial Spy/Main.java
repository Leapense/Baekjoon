import java.io.*;
import java.util.*;

public class Main {
    static final int LIMIT = 10_000_000;
    static boolean[] isPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        while (first != null && first.trim().isEmpty()) first = br.readLine();
        int T = Integer.parseInt(first.trim());

        sieve(LIMIT - 1);

        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) line = br.readLine();
            String s =  line.trim();

            int[] cnt = new int[10];
            for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - '0']++;

            HashSet<Integer> made = new HashSet<>();
            dfsBuild(cnt, 0, made);

            int ans = 0;
            for (int x : made) {
                if (x < LIMIT && isPrime[x]) ans++;
            }
            out.append(ans).append('\n');
        }

        System.out.print(out.toString());
    }

    static void dfsBuild(int[] cnt, int cur, HashSet<Integer> made) {
        for (int d = 0; d <= 9; d++) {
            if (cnt[d] == 0) continue;
            cnt[d]--;
            int next = cur * 10 + d;
            made.add(next);
            dfsBuild(cnt, next, made);
            cnt[d]++;
        }
    }

    static void sieve(int maxN) {
        isPrime = new boolean[maxN + 1];
        Arrays.fill(isPrime, true);
        if (maxN >= 0) isPrime[0] = false;
        if (maxN >= 1) isPrime[1] = false;
        int r = (int)Math.sqrt(maxN);
        for (int i = 2; i <= r; i++) {
            if (!isPrime[i]) continue;
            for (int m = i * i; m <= maxN; m += i) {
                isPrime[m] = false;
            }
        }
    }
}