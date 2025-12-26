import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

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
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return null;
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
                if (c == -1) break;
            }
            return sb.toString();
        }

        int nextInt() throws IOException {
            String s = next();
            return Integer.parseInt(s);
        }

        long nextLong() throws IOException {
            String s = next();
            return Long.parseLong(s);
        }
    }

    static int[] head, to, next;
    static double[] delta;
    static int edgePtr = 0;

    static void addEdge(int u, int v, double d) {
        to[edgePtr] = v;
        delta[edgePtr] = d;
        next[edgePtr] = head[u];
        head[u] = edgePtr++;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String first = fs.next();
        if (first == null) return;
        int N = Integer.parseInt(first);

        int maxV = 2 * N + 5;
        head = new int[maxV];
        Arrays.fill(head, -1);

        to = new int[2 * N];
        next = new int[2 * N];
        delta = new double[2 * N];

        HashMap<String, Integer> id = new HashMap<>(maxV * 2);
        ArrayList<String> names = new ArrayList<>(maxV);
        for (int i = 0; i < N; i++) {
            String a = fs.next();
            String b = fs.next();
            long x = fs.nextLong();

            int ia = getId(a, id, names);
            int ib = getId(b, id, names);

            double lx = Math.log((double) x);
            addEdge(ia, ib, -lx);
            addEdge(ib, ia, +lx);
        }

        int V = names.size();
        double[] logVal = new double[V];
        boolean[] vis = new boolean[V];
        
        int[] q = new int[V];
        int qs = 0, qe = 0;
        
        vis[0] = true;
        logVal[0] = 0.0;
        q[qe++] = 0;

        while (qs < qe) {
            int u = q[qs++];
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (!vis[v]) {
                    vis[v] = true;
                    logVal[v] = logVal[u] + delta[e];
                    q[qe++] = v;
                }
            }
        }

        int idxMax = 0, idxMin = 0;
        double maxLog = logVal[0], minLog = logVal[0];
        for (int i = 1; i < V; i++) {
            if (logVal[i] > maxLog) {
                maxLog = logVal[i];
                idxMax = i;
            }
            if (logVal[i] < minLog) {
                minLog = logVal[i];
                idxMin = i;
            }
        }

        double ratio = Math.exp(maxLog - minLog);

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)));
        out.print(names.get(idxMax));
        out.print(' ');
        out.print(names.get(idxMin));
        out.print(' ');
        out.printf("%.2f", ratio);
        out.println();
        out.flush();
    }

    private static int getId(String name, HashMap<String, Integer> id, ArrayList<String> names) {
        Integer v = id.get(name);
        if (v != null) return v;
        int idx = names.size();
        id.put(name, idx);
        names.add(name);
        return idx;
    }
}