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

    private static class Node implements Comparable<Node> {
        int v;
        long d;
        Node(int v, long d) { this.v = v; this.d = d; }
        public int compareTo(Node o) {
            return Long.compare(this.d, o.d);
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int[] L = new int[N + 1];

        int maxE = N * N + 5;
        int[] head = new int[N + 1];
        int[] to = new int[maxE];
        int[] next = new int[maxE];
        Arrays.fill(head, -1);
        int ec = 0;

        for (int i = 1; i <= N; i++) {
            L[i] = fs.nextInt();
            int M = fs.nextInt();
            for (int j = 0; j < M; j++) {
                int a = fs.nextInt();
                to[ec] = a;
                next[ec] = head[i];
                head[i] = ec;
                ec++;
            }
        }

        final long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v;
            long d = cur.d;
            if (d != dist[u]) continue;

            long ndBase = d + L[u];
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (ndBase < dist[v]) {
                    dist[v] = ndBase;
                    pq.add(new Node(v, ndBase));
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            long finish = dist[i] + L[i];
            if (finish > ans) ans = finish;
        }

        System.out.println(ans);
    }
}