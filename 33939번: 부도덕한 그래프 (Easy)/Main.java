import java.io.*;
import java.util.*;

public class Main {
    private static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;
        FastScanner(InputStream in) {this.in = in;}
        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do { c = readByte(); } while (c <= ' ');
            if (c == '-') { sign = -1; c = readByte(); }
            for (; c > ' '; c = readByte()) val = val * 10 + (c - '0');
            return val * sign;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int M = fs.nextInt();

        boolean[][] adj = new boolean[N + 1][N + 1];
        List<Integer>[] parents = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            if (!adj[u][v]) {
                adj[u][v] = true;
                parents[v].add(u);
            }
        }

        long ans = 0;
        for (int z = 1; z <= N; z++) {
            List<Integer> p = parents[z];
            int k = p.size();
            for (int i = 0; i < k; i++) {
                int x = p.get(i);
                for (int j = i + 1; j < k; j++) {
                    int y = p.get(j);
                    if (!adj[x][y] && !adj[y][x]) ans++;
                }
            }
        }

        System.out.println(ans);
    }
}