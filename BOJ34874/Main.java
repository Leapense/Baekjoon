import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int M = fs.nextInt();

        long[] answer = new long[M];

        int[] row = new int[M];
        int[] cnt = new int[6];
        int[] greater = new int[6];

        for (int i = 0; i < N; i++) {
            Arrays.fill(cnt, 0);

            for (int j = 0; j < M; j++) {
                row[j] = fs.nextInt();
                cnt[row[j]]++;
            }

            greater[5] = 0;
            for (int t = 4; t >= 1; t--) {
                greater[t] = greater[t + 1] + cnt[t + 1];
            }

            for (int j = 0; j < M; j++) {
                int current = row[j];
                int best = Integer.MAX_VALUE;

                for (int t = 1; t <= 5; t++) {
                    int cost = 0;
                    if (current != t) cost++;

                    int othersGreater = greater[t];
                    if (current > t) {
                        othersGreater--;
                    }

                    cost += othersGreater;
                    best = Math.min(best, cost);
                }

                answer[j] += best;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < M; j++) {
            if (j > 0) sb.append(' ');
            sb.append(answer[j]);
        }

        System.out.println(sb);
    }
}