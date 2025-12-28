import java.io.*;
import java.util.*;

public class Main {
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
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
                c = readByte();
                if (c == -1) return Integer.MIN_VALUE;
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }

            return val * sign;
        }
    }

    private static int solveOneCase(int n, int L, int[] items) {
        Arrays.sort(items);
        int i = 0, j = n - 1;
        int bins = 0;

        while (i <= j) {
            bins++;
            if (i < j && items[i] + items[j] <= L) {
                i++;
                j--;
            } else {
                j--;
            }
        }

        return bins;
    }

    private static boolean canParseAsMultiCase(int[] tokens, int size) {
        if (size < 3) return false;
        int T = tokens[0];
        if (T <= 0) return false;

        int idx = 1;
        for (int tc = 0; tc < T; tc++) {
            if (idx + 1 >= size) return false;
            int n = tokens[idx++];
            int L = tokens[idx++];

            if (n < 1) return false;
            if (idx + n > size) return false;
            idx += n;
        }
        return idx == size;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int cap = 1 << 20;
        int[] tokens = new int[cap];
        int size = 0;

        while (true) {
            int v = fs.nextInt();
            if (v == Integer.MIN_VALUE) break;
            if (size == tokens.length) {
                tokens = Arrays.copyOf(tokens, tokens.length * 2);
            }

            tokens[size++] = v;
        }

        if (size == 0) return;

        StringBuilder sb = new StringBuilder();
        if (canParseAsMultiCase(tokens, size)) {
            int T = tokens[0];
            int idx = 1;
            for (int tc = 1; tc <= T; tc++) {
                int n = tokens[idx++];
                int L = tokens[idx++];

                int[] items = new int[n];
                for (int i = 0; i < n; i++) items[i] = tokens[idx++];

                int ans = solveOneCase(n, L, items);
                sb.append(ans);

                if (tc < T) sb.append("\n\n");
                else sb.append('\n');
            }
        } else {
            if (size < 2) return;
            int n = tokens[0];
            int L = tokens[1];
            int[] items = new int[n];

            for (int i = 0; i < n && 2 + i < size; i++) items[i] = tokens[2 + i];
            int ans = solveOneCase(n, L, items);
            sb.append(ans).append('\n');
        }

        System.out.print(sb.toString());
    }
}