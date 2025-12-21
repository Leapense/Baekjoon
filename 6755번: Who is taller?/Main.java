import java.io.*;
import java.util.*;

public class Main {
    static final class FastScanner {
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
            } while (c <= ' ' && c != -1);

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

    static boolean reachable(int start, int target,
            int[] head, int[] to, int[] next,
            int[] visited, int mark,
            int[] stack) {
        int top = 0;
        visited[start] = mark;
        stack[top++] = start;

        while (top > 0) {
            int v = stack[--top];
            for (int e = head[v]; e != -1; e = next[e]) {
                int u = to[e];
                if (u == target)
                    return true;
                if (visited[u] != mark) {
                    visited[u] = mark;
                    stack[top++] = u;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        int M = fs.nextInt();

        int[] head = new int[N + 1];
        Arrays.fill(head, -1);

        int[] to = new int[M];
        int[] next = new int[M];

        for (int i = 0; i < M; i++) {
            int x = fs.nextInt();
            int y = fs.nextInt();
            to[i] = y;
            next[i] = head[x];
            head[x] = i;
        }

        int p = fs.nextInt();
        int q = fs.nextInt();

        int[] visited = new int[N + 1];
        int[] stack = new int[N + 5];
        int mark = 1;

        if (reachable(p, q, head, to, next, visited, mark++, stack)) {
            System.out.print("yes");
        } else if (reachable(q, p, head, to, next, visited, mark++, stack)) {
            System.out.print("no");
        } else {
            System.out.print("unknown");
        }
    }
}