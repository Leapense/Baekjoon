import java.io.*;
import java.util.*;

public class Main {
    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return Math.abs(a);
    }

    static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return  a / gcd(a, b) * b;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        long N = Long.parseLong(s);

        long L = 1;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) L = lcm(L, d);
        }

        if (L == 1) {
            System.out.println(N);
            return;
        }

        long pow10 = 1;
        for (int k = 0; ; k++) {
            long Nm = N % L;
            long p10m = pow10 % L;
            long r = (L - (Nm * p10m) % L) % L;

            if (r < pow10) {
                long ans = N * pow10 + r;
                System.out.println(ans);
                break;
            }
            pow10 *= 10;
        }
    }
}