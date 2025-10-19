import java.io.*;
import java.util.*;

public class Main {
    static long A;
    static int N;
    static HashMap<Key, Long> memo = new HashMap<>();

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        long p = fs.nextLong();
        long q = fs.nextLong();
        A = fs.nextLong();
        N = fs.nextInt();

        long g = gcd(p, q);
        p /= g; q /= g;

        long ans = dfs(p, q, 1, N, (int) Math.min(A, Integer.MAX_VALUE));
        System.out.println(ans);
    }

    static long dfs(long p, long q, int last, int r, int limit) {
        if (p == 0) return 1L;
        if (r == 0) return 0L;
        if (limit <= 0) return 0L;


        Key key = new Key(p, q, last, r, limit);
        Long cached = memo.get(key);
        if (cached != null) return cached;

        long lbEgypt = ceilDiv(q, p);
        int lb = (int)Math.max(last, Math.min(lbEgypt, Integer.MAX_VALUE));
        long ubSum = (r * q) / p;
        int ub = (int) Math.min(Math.min(ubSum, (long) limit), Integer.MAX_VALUE);

        if (lb > ub) {
            memo.put(key, 0L);
            return 0L;
        }

        long ways = 0L;
        for (int x = lb; x <= ub; x++) {
            long num = p * x - q;
            long den = q * x;
            if (num < 0) continue;

            long g = gcd(num, den);
            long p2 = num / g;
            long q2 = den / g;

            int newLimit = limit / x;
            if (newLimit <= 0 && p2 > 0) continue;

            ways += dfs(p2, q2, x, r - 1, newLimit);
        }

        memo.put(key, ways);
        return ways;
    }

    static long gcd(long a, long b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }

        return a;
    }

    static long ceilDiv(long a, long b) {
        return (a + b - 1) / b;
    }

    static final class Key {
        final long p;
        final long q;
        final int last;
        final int r;
        final int limit;

        Key(long p, long q, int last, int r, int limit) {
            this.p = p;
            this.q = q;
            this.last = last;
            this.r = r;
            this.limit = limit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return p == key.p && q == key.q && r == key.r && limit == key.limit;
        }

        @Override
        public int hashCode() {
            int result = (int) (p ^ (p >>> 32));
            result = 31 * result + (int)(q ^ (q >>> 32));
            result = 31 * result + last;
            result = 31 * result + r;
            result = 31 * result + limit;
            return result;
        }
    }

    static final class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        long nextLong() throws IOException { return Long.parseLong(next()); }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }
}