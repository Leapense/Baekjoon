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

    static int N, M;
    static byte[] grid;
    static int[] visited;
    static int stamp = 0;
    static int[] queue;

    static boolean canEscape(int mask) {
        stamp++;
        int head = 0, tail = 0;
        int base = 0;
        for (int c = 0; c < N; c++) {
            int idx = base + c;
            int d = grid[idx] & 0xFF;

            if ((mask & (1 << d)) != 0) {
                visited[idx] = stamp;
                queue[tail++] = idx;
            }
        }

        while (head < tail) {
            int idx = queue[head++];
            int r = idx / N;
            int c = idx - r * N;

            if (r == M - 1)
                return true;
            if (r > 0) {
                int nidx = idx - N;
                if (visited[nidx] != stamp) {
                    int d = grid[nidx] & 0xFF;
                    if ((mask & (1 << d)) != 0) {
                        visited[nidx] = stamp;
                        queue[tail++] = nidx;
                    }
                }
            }

            if (r + 1 < M) {
                int nidx = idx + N;
                if (visited[nidx] != stamp) {
                    int d = grid[nidx] & 0xFF;
                    if ((mask & (1 << d)) != 0) {
                        visited[nidx] = stamp;
                        queue[tail++] = nidx;
                    }
                }
            }

            if (c > 0) {
                int nidx = idx - 1;
                if (visited[nidx] != stamp) {
                    int d = grid[nidx] & 0xFF;
                    if ((mask & (1 << d)) != 0) {
                        visited[nidx] = stamp;
                        queue[tail++] = nidx;
                    }
                }
            }

            if (c + 1 < N) {
                int nidx = idx + 1;
                if (visited[nidx] != stamp) {
                    int d = grid[nidx] & 0xFF;
                    if ((mask & (1 << d)) != 0) {
                        visited[nidx] = stamp;
                        queue[tail++] = nidx;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        N = fs.nextInt();
        M = fs.nextInt();

        if (N == Integer.MIN_VALUE || M == Integer.MIN_VALUE)
            return;

        int total = N * M;
        grid = new byte[total];
        visited = new int[total];
        queue = new int[total];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                int v = fs.nextInt();
                grid[r * N + c] = (byte) v;
            }
        }

        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    int mask = (1 << a) | (1 << b) | (1 << c);
                    if (canEscape(mask)) {
                        System.out.println(a + " " + b + " " + c);
                        return;
                    }
                }
            }
        }

        System.out.println("-1 -1 -1");
    }
}