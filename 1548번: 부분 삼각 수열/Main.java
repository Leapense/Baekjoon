import java.io.*;
import java.util.*;

public class Main {
    static int lowerBound(long[] a, long key) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= key) r = m;
            else l = m + 1;
        }
        return l;
    }

    static int upperBound(long[] a, long key) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] > key) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();
        if (line == null) return;

        int N = Integer.parseInt(line.trim());
        long[] a = new long[N];
        int filled = 0;
        StringTokenizer st = null;
        while (filled < N) {
            if (st == null || !st.hasMoreTokens()) {
                String s = br.readLine();
                if (s == null) break;
                st = new StringTokenizer(s);
                continue;
            }
            a[filled++] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(a);

        if (N <= 2) {
            System.out.println(N);
            return;
        }

        int ans = 2;
        int n = N;
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && a[j] == a[i]) j++;
            int cntSame = j - i;
            long v1 = a[i];

            if (cntSame >= 2) {
                long T = v1 * 2L;
                int hi = lowerBound(a, T);
                int candidate = hi - i;
                if (candidate > ans) ans = candidate;
            }
            for (int t = j; t < n; ) {
                long v2 = a[t];
                int t2 = t + 1;
                while (t2 < n && a[t2] == v2) t2++;
                long T = v1 + v2;
                int upV2 = t2;
                int hi = lowerBound(a, T);
                int eqV2 = t2 - t;
                int greater = hi - upV2;
                int candidate = 1 + eqV2 + greater;
                if (candidate > ans) ans = candidate;
                t = t2;
            }
            i = j;
        }
        System.out.println(ans);
    }
}