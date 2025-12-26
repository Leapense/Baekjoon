import java.io.*;
import java.util.*;

public class Main {
    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) { this.in = in; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return Integer.MIN_VALUE;
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    private static long maxSubarrayAtMostL(long[] pref, int n, int L) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.addLast(0);

        long best = Long.MIN_VALUE;
        for (int j = 1; j <= n; j++) {
            int minI = j - L;
            while (!dq.isEmpty() && dq.peekFirst() < minI) dq.pollFirst();
            long cand = pref[j] - pref[dq.peekFirst()];
            if (cand > best) best = cand;

            while (!dq.isEmpty() && pref[dq.peekLast()] >= pref[j]) dq.pollLast();
            dq.addLast(j);
        }

        return best;
    }

    private static long[] prefixBestAtMostK(long[] a, int n, int K) {
        long[] pref = new long[n + 1];
        for (int i = 1; i <= n; i++) pref[i] = pref[i - 1] + a[i];

        long[] bestEnd = new long[n + 1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.addLast(0);

        for (int j = 1; j <= n; j++) {
            int minI = j - K;
            while (!dq.isEmpty() && dq.peekFirst() < minI) dq.pollFirst();
            bestEnd[j] = pref[j] - pref[dq.peekFirst()];
            while (!dq.isEmpty() && pref[dq.peekLast()] >= pref[j]) dq.pollLast();
            dq.addLast(j);
        }

        long[] prefixBest = new long[n + 1];
        prefixBest[0] = Long.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            prefixBest[i] = (i == 1) ? bestEnd[i] : Math.max(prefixBest[i - 1], bestEnd[i]);
        }
        return prefixBest;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int K = fs.nextInt();

        long[] a = new long[N + 1];
        long total = 0;
        for (int i = 1; i <= N; i++) {
            a[i] = fs.nextInt();
            total += a[i];
        }

        long[] pref = new long[N + 1];
        for (int i = 1; i <= N; i++) pref[i] = pref[i - 1] + a[i];

        int L2 = Math.min(2 * K, N);
        long bestOne = maxSubarrayAtMostL(pref, N, L2);

        int L1 = Math.min(K, N);
        long[] leftBest = prefixBestAtMostK(a, N, L1);

        long[] rev = new long[N + 1];
        for (int i = 1; i <= N; i++) rev[i] = a[N - i + 1];
        long[] rightBestRev = prefixBestAtMostK(rev, N, L1);

        long[] rightBest = new long[N + 2];
        for (int i = 1; i <= N; i++) {
            int t = N - i + 1;
            rightBest[i] = rightBestRev[t];
        }

        long bestTwo = Long.MIN_VALUE;
        for (int s = 1; s <= N - 1; s++) {
            long cand = leftBest[s] + rightBest[s + 1];
            if (cand > bestTwo) bestTwo = cand;
        }

        long ans = Math.max(bestOne, bestTwo);
        System.out.println(ans);
    }
}