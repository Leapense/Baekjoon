import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
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
                if (len <= 0)
                    return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1)
                    return Integer.MIN_VALUE;
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

    static int[] head;
    static int[] to;
    static int[] next;
    static int edgeCnt = 0;

    static void addEdge(int u, int v) {
        if (edgeCnt == to.length) {
            int newCap = to.length << 1;
            to = Arrays.copyOf(to, newCap);
            next = Arrays.copyOf(next, newCap);
        }
        to[edgeCnt] = v;
        next[edgeCnt] = head[u];
        head[u] = edgeCnt;
        edgeCnt++;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        if (n == Integer.MIN_VALUE)
            return;

        head = new int[n + 1];
        Arrays.fill(head, -1);

        to = new int[1024];
        next = new int[1024];

        while (true) {
            int x = fs.nextInt();
            int y = fs.nextInt();
            if (x == Integer.MIN_VALUE || y == Integer.MIN_VALUE)
                break;
            if (x == 0 && y == 0)
                break;
            addEdge(x, y);
        }

        long[] dp = new long[n + 1];
        dp[n] = 1;

        for (int i = n - 1; i >= 1; i--) {
            long sum = 0;
            for (int e = head[i]; e != -1; e = next[e]) {
                sum += dp[to[e]];
            }
            dp[i] = sum;
        }

        System.out.println(dp[1]);
    }
}