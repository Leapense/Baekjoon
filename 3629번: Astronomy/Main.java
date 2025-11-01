import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sgn = 1, x = 0;
            do { c = read(); } while (c <= ' ' && c != -1);
            if (c == '-') { sgn = -1; c = read(); }
            for (; c > ' '; c = read()) x = x * 10 + (c - '0');
            return x * sgn;
        }
    }

    static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    static BigInteger lcm(BigInteger a, BigInteger b) {
        if (a.signum() == 0 || b.signum() == 0) return BigInteger.ZERO;
        return a.divide(a.gcd(b)).multiply(b);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int[] t = new int[n];
        for (int i = 0; i < n; i++) t[i] = fs.nextInt();

        int base = t[0];
        List<BigInteger> numList = new ArrayList<>();
        List<BigInteger> denList = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (t[i] == base) continue;
            long numL = 1L * base * t[i];
            long denL = 2L * Math.abs(base - t[i]);
            long g = gcdLong(numL, denL);
            numL /= g; denL /= g;
            numList.add(BigInteger.valueOf(numL));
            denList.add(BigInteger.valueOf(denL));
        }

        BigInteger Lnum = numList.get(0);
        for (int i = 1; i < numList.size(); i++) Lnum = lcm(Lnum, numList.get(i));

        BigInteger Lden = denList.get(0);
        for (int i = 1; i < denList.size(); i++) Lden = gcd(Lden, denList.get(i));
        
        BigInteger gFinal = Lnum.gcd(Lden);
        
        Lnum = Lnum.divide(gFinal);
        Lden = Lden.divide(gFinal);

        System.out.println(Lnum + " " + Lden);
    }

    static long gcdLong(long a, long b) {
        a = Math.abs(a); b = Math.abs(b);
        while (b != 0) {
            long t = a % b; a = b; b = t;
        }

        return a;
    }
}