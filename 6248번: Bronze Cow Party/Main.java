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
    }

    static int[] head, to, w, next;
    static int edgeIdx;

    static void addEdge(int u, int v, int cost) {
        to[edgeIdx] = v;
        w[edgeIdx] = cost;
        next[edgeIdx] = head[u];
        head[u] = edgeIdx++;
    }

    static class Node {
        int v;
        long d;

        Node(int v, long d) {
            this.v = v;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int M = fs.nextInt();
        int X = fs.nextInt();

        head = new int[N + 1];
        Arrays.fill(head, -1);

        to = new int[2 * M];
        w = new int[2 * M];
        next = new int[2 * M];
        edgeIdx = 0;

        for (int i = 0; i < M; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int t = fs.nextInt();
            addEdge(a, b, t);
            addEdge(b, a, t);
        }

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.d));
        pq.add(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int v = cur.v;
            long d = cur.d;

            if (d != dist[v])
                continue;

            for (int e = head[v]; e != -1; e = next[e]) {
                int nv = to[e];
                long nd = d + w[e];
                if (nd < dist[nv]) {
                    dist[nv] = nd;
                    pq.add(new Node(nv, nd));
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist[i] * 2);
        }

        System.out.print(ans);
    }
}