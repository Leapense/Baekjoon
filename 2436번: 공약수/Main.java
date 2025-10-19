import java.io.*;
import java.util.*;

public class Main {
    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long G = Long.parseLong(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        long M = L / G;
        long root = (long) Math.sqrt(M);

        long x = 1, y = M;
        for (long d = root; d >= 1; d--) {
            if (M % d == 0) {
                long q = M / d;
                if (gcd(d, q) == 1) {
                    x = d;
                    y = q;
                    break;
                }
            }
        }

        long A = G * x;
        long B = G * y;
        if (A > B) {
            long t = A;
            A = B;
            B = t;
        }
        System.out.println(A + " " + B);
    }
}