import java.io.*;
import java.util.*;

public class Main {
    static long d1, d2, d3;
    static long l12, l13, l23, l123;

    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }

        return a;
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long playableUpTo(long x) {
        if (x <= 0) return 0;
        long a = x / d1;
        long b = x / d2;
        long c = x / d3;
        long ab = x / l12;
        long ac = x / l13;
        long bc = x / l23;
        long abc = x / l123;
        long blocked = a + b + c - ab - ac - bc + abc;
        return x - blocked;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d1 = Long.parseLong(st.nextToken());
        d2 = Long.parseLong(st.nextToken());
        d3 = Long.parseLong(st.nextToken());
        long n = Long.parseLong(br.readLine().trim());

        l12 = lcm(d1, d2);
        l13 = lcm(d1, d3);
        l23 = lcm(d2, d3);
        l123 = lcm(l12, d3);

        long lo = 1, hi = 1;
        while (playableUpTo(hi) < n) {
            hi <<= 1;
        }

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (playableUpTo(mid) >= n) hi = mid;
            else lo = mid + 1;
        }

        System.out.println(lo);
    }
}