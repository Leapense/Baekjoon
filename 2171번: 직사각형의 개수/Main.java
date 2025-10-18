import java.io.*;
import java.util.*;

public class Main {
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }
        private int read() throws IOException {
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
                c = read();
            } while (c <= 32);

            int sign = 1;
            if (c == '-') { sign = -1; c = read(); }
            long val = 0;
            while (c > 32) {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    static final class IntIntHashMap {
        int n;
        int size;
        int mask;
        int threshold;
        int[] keys;
        int[] vals;
        boolean[] used;

        IntIntHashMap(int expectedSize) {
            int cap = 1;
            long need = Math.max(4L, (long)Math.ceil(expectedSize / 0.7));
            while (cap < need && cap < (1 << 30)) cap <<= 1;
            init(cap);
        }

        private void init(int cap) {
            n = cap;
            mask = n - 1;
            threshold = (int)(n * 0.7);
            keys = new int[n];
            vals = new int[n];
            used = new boolean[n];
            size = 0;
        }

        private int mix(int x) {
            x ^= (x >>> 16);
            x *= 0x7feb352d;
            x ^= (x >>> 15);
            x *= 0x846ca68b;
            x ^= (x >>> 16);
            return x;
        }

        private void rehash() {
            int[] oldK = keys;
            int[] oldV = vals;
            boolean[] oldU = used;
            init(n << 1);
            for (int i = 0; i < oldK.length; i++) {
                if (oldU[i]) {
                    putRehash(oldK[i], oldV[i]);
                }
            }
        }

        private void putRehash(int key, int val) {
            int idx = mix(key) & mask;
            while (used[idx]) {
                idx = (idx + 1) & mask;
            }

            used[idx] = true;
            keys[idx] = key;
            vals[idx] = val;
            size++;
        }

        int get(int key) {
            int idx = mix(key) & mask;
            while (used[idx]) {
                if (keys[idx] == key) return vals[idx];
                idx = (idx + 1) & mask;
            }
            return 0;
        }

        void addOne(int key) {
            int idx = mix(key) & mask;
            while (used[idx]) {
                if (keys[idx] == key) {
                    vals[idx]++;
                    return;
                }
                idx = (idx + 1) & mask;
            }
            used[idx] = true;
            keys[idx] = key;
            vals[idx] = 1;
            if (++size > threshold) rehash();
        }
    }

    private static long comb2(int k) {
        return (k < 2) ? 0L : (long)k * (k - 1) / 2;
    }
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        long[] xs = new long[N];
        long[] ys = new long[N];
        for (int i = 0; i < N; i++) {
            xs[i] = fs.nextLong();
            ys[i] = fs.nextLong();
        }

        long[] xVals = xs.clone();
        Arrays.sort(xVals);
        int Ux = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0 || xVals[i] != xVals[i - 1]) xVals[Ux++] = xVals[i];
        }

        xVals = Arrays.copyOf(xVals, Ux);

        long[] yVals = ys.clone();
        Arrays.sort(yVals);
        int Uy = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0 || yVals[i] != yVals[i - 1]) yVals[Uy++] = yVals[i];
        }

        yVals = Arrays.copyOf(yVals, Uy);

        int[] xId = new int[N];
        int[] yId = new int[N];
        for (int i = 0; i < N; i++) {
            xId[i] = Arrays.binarySearch(xVals, xs[i]);
            yId[i] = Arrays.binarySearch(yVals, ys[i]);
        }

        int[] cntPerY = new int[Uy];
        for (int i = 0; i < N; i++) cntPerY[yId[i]]++;
        int[][] rows = new int[Uy][];
        int[] pos = new int[Uy];
        for (int y = 0; y < Uy; y++) rows[y] = new int[cntPerY[y]];
        for (int i = 0; i < N; i++) {
            rows[yId[i]][pos[yId[i]]++] = xId[i];
        }
        for (int y = 0; y < Uy; y++) {
            Arrays.sort(rows[y]);
        }

        int B = (int)Math.sqrt(N);
        if (B < 1) B = 1;

        boolean[] isHeavy = new boolean[Uy];
        List<Integer> heavyList = new ArrayList<>();
        for (int y = 0; y < Uy; y++) {
            if (rows[y].length > B) {
                isHeavy[y] = true;
                heavyList.add(y);
            }
        }

        long answer = 0L;
        boolean[] present = new boolean[Ux];
        boolean[] isHeavyRow = isHeavy;

        for (int idx = 0; idx < heavyList.size(); idx++) {
            int hy = heavyList.get(idx);
            int[] hx = rows[hy];
            for (int x : hx) present[x] = true;
            for (int ry = 0; ry < Uy; ry++) {
                if (ry == hy) continue;
                if (isHeavyRow[ry] && ry < hy) continue;
                int common = 0;
                int[] rx = rows[ry];
                for (int x : rx) if (present[x]) common++;
                if (common >= 2) answer += comb2(common);
            }

            for (int x : hx) present[x] = false;
        }

        long estPairs = (long) N * B / 2 + 4;
        IntIntHashMap pairCount = new IntIntHashMap((int)Math.min(estPairs, 1_200_000L));

        for (int y = 0; y < Uy; y++) {
            if (isHeavyRow[y]) continue;
            int[] xsRow = rows[y];
            int m = xsRow.length;
            for (int i = 0; i < m; i++) {
                int xi = xsRow[i];
                for (int j = i + 1; j < m; j++) {
                    int xj = xsRow[j];
                    int key = xi * Ux + xj;
                    int seen = pairCount.get(key);
                    answer += seen;
                    pairCount.addOne(key);
                }
            }
        }

        System.out.println(answer);
    }
}