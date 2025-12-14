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

        String next() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int R = fs.nextInt();
        int C = fs.nextInt();

        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            String row = fs.next();
            grid[i] = row.toCharArray();
        }

        int n = R * C;
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int start = 0;
        int target = (R - 1) * C + (C - 1);

        int[] q = new int[n];
        int head = 0, tail = 0;

        visited[start] = true;
        q[tail++] = start;

        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        while (head < tail && !visited[target]) {
            int cur = q[head++];
            int r = cur / C;
            int c = cur % C;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                    continue;
                if (grid[nr][nc] == '*')
                    continue;

                int nxt = nr * C + nc;
                if (visited[nxt])
                    continue;

                visited[nxt] = true;
                parent[nxt] = cur;
                q[tail++] = nxt;
            }
        }

        int[] path = new int[n];
        int pathLen = 0;

        for (int cur = target; cur != -1; cur = parent[cur]) {
            path[pathLen++] = cur;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = pathLen - 1; i >= 0; i--) {
            int idx = path[i];
            int rr = idx / C + 1;
            int cc = idx % C + 1;
            sb.append(rr).append(' ').append(cc).append('\n');
        }

        System.out.print(sb.toString());
    }
}