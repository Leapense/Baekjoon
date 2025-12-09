import java.io.*;
import java.util.*;

public class Main {
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            this.in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) {
                    return -1;
                }
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    private static class Pair {
        long h;
        int idx;
    }

    private static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }

            return x;
        }

        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return false;
            }
            if (size[ra] < size[rb]) {
                int tmp = ra;
                ra = rb;
                rb = tmp;
            }
            parent[rb] = ra;
            size[ra] += size[rb];
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long tmp = fs.nextLong();
        if (tmp == -1) {
            return;
        } 
        int N = (int) tmp;

        Pair[] arr = new Pair[N];
        for (int i = 0; i < N; i++) {
            long h = fs.nextLong();
            Pair p = new Pair();
            p.h = h;
            p.idx = i;
            arr[i] = p;
        }

        Arrays.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                return Long.compare(b.h, a.h);
            }
        });

        DSU dsu = new DSU(N);
        boolean[] active = new boolean[N];
        int components = 0;
        int maxIslands = 0;

        int i = 0;
        while (i < N) {
            long h = arr[i].h;
            int j = i;

            while (j < N && arr[j].h == h) {
                int idx = arr[j].idx;
                active[idx] = true;
                components++;

                if (idx > 0 && active[idx - 1]) {
                    if (dsu.union(idx, idx - 1)) {
                        components--;
                    }
                }

                if (idx + 1 < N && active[idx + 1]) {
                    if (dsu.union(idx, idx + 1)) {
                        components--;
                    }
                }

                j++;
            }

            if (components > maxIslands) {
                maxIslands = components;
            }
            i = j;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxIslands).append('\n');
        System.out.println(sb.toString());
    }
}