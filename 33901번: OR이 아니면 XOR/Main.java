import java.io.*;
import java.util.*;

public class Main {
    static final class FastScanner {
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
            int c, sgn = 1, val = 0;
            do { c = read(); } while (c <= ' ');
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sgn;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        int M = fs.nextInt();
        int K = fs.nextInt();

        int[] A = new int[N];
        for (int i = 0; i < N; i++) A[i] = fs.nextInt();

        final int MAXV = 1 << 17;
        int[] freq = new int[MAXV];

        long ans = 0L;
        for (int j = 0; j < N; j++) {
            int outIdx = j - M - 1;
            if (outIdx >= 0) {
                int outVal = A[outIdx];
                freq[outVal]--;
            }

            int need = A[j] ^ K;
            ans += freq[need];

            freq[A[j]]++;
        }

        System.out.print(ans);
    }
}