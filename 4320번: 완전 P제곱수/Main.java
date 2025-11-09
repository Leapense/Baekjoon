import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> primes = new ArrayList<>();
    static void sieve(int max) {
        boolean[] comp = new boolean[max + 1];
        for (int i = 2; i * i <= max; i++) {
            if (!comp[i]) {
                for (int j = i * i; j <= max; j += i) {
                    comp[j] = true;
                }
            }
        }
        for (int i = 2; i <= max; i++) {
            if (!comp[i]) primes.add(i);
        }
    }


    static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }

        return a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        sieve(46340);
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            line = line.trim();
            if (line.isEmpty()) continue;

            long x = Long.parseLong(line);
            if (x == 0) break;
            boolean neg = x < 0;
            long n = (x < 0) ? -x : x;

            int g = 0;
            long m = n;

            for (int p : primes) {
                long pp = (long) p * (long) p;
                if (pp > m) break;
                if (m % p == 0) {
                    int e = 0;
                    while (m % p == 0) {
                        m /= p;
                        e++;
                    }
                    g = (g == 0) ? e : gcd(g, e);
                }
            }

            if (m > 1) {
                g = (g == 0) ? 1 : gcd(g, 1);
            }

            if (neg) {
                while ((g & 1) == 0) g >>= 1;
            }

            out.append(g).append('\n');
        }

        System.out.print(out.toString());
    }
}