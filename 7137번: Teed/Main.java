import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) { this.in = in; }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return Long.MIN_VALUE;
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    static class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
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

        void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return;
            if (size[ra] < size[rb]) {
                int t = ra; ra = rb; rb = t;
            }

            parent[rb] = ra;
            size[ra] += size[rb];
        }
    }

    static long comb2(long x) {
        return x * (x - 1) / 2;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        long N1 = fs.nextLong();
        if (N1 == Long.MIN_VALUE) return;
        int N = (int) N1;
        int M = (int) fs.nextLong();
        long K = fs.nextLong();

        int[] U = new int[M];
        int[] V = new int[M];

        DSU dsu = new DSU(N);

        for (int i = 0; i < M; i++) {
            int a = (int) fs.nextLong();
            int b = (int) fs.nextLong();
            U[i] = a;
            V[i] = b;
            dsu.union(a, b);
        }

        HashMap<Integer, Integer> idxMap = new HashMap<>(N * 2);
        int[] compSizeTemp = new int[N];
        int compCnt = 0;

        for (int i = 1; i <= N; i++) {
            int r = dsu.find(i);
            Integer idx = idxMap.get(r);
            if (idx == null) {
                idxMap.put(r, compCnt);
                compSizeTemp[compCnt] = dsu.size[r];
                compCnt++;
            }
        }

        int[] edgeCnt = new int[compCnt];
        for (int i = 0; i < M; i++) {
            int r = dsu.find(U[i]);
            int idx = idxMap.get(r);
            edgeCnt[idx]++;
        }

        long C0 = compCnt;
        long minRegions = (K >= C0 - 1) ? 1L : (C0 - K);
        long internalMissing = 0;
        long[] sizes = new long[compCnt];
        for (int i = 0; i < compCnt; i++) {
            long s = compSizeTemp[i];
            sizes[i] = s;
            long missing = comb2(s) - (long) edgeCnt[i];
            internalMissing += missing;
        }

        long maxRegions;
        if (K <= internalMissing) {
            maxRegions = C0;
        } else {
            long R = K - internalMissing;

            Arrays.sort(sizes);
            long[] desc = new long[compCnt];
            for (int i = 0; i < compCnt; i++) {
                desc[i] = sizes[compCnt - 1 - i];
            }

            long[] ps = new long[compCnt + 1];
            long[] psq = new long[compCnt + 1];
            for (int i = 1; i <= compCnt; i++) {
                long x = desc[i - 1];
                ps[i] = ps[i - 1] + x;
                psq[i] = psq[i - 1] + x * x;
            }

            int lo = 2, hi = compCnt;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                long S = ps[mid];
                long Q = psq[mid];
                long cross = (S * S - Q) / 2;
                if (cross >= R) hi = mid;
                else lo = mid + 1;
            }

            int k = lo;
            maxRegions = C0 - k + 1;
        }

        System.out.println(minRegions + " " + maxRegions);
    }
}