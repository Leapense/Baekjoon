import java.io.*;
import java.util.*;

public class Main {
    private static final class FastScanner {
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
            do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == -1) throw new EOFException();
            long sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }

            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    private static final class State {
        final long sVotes;
        final long[] others;
        final int depth;

        State(long sVotes, long[] others, int depth) {
            this.sVotes = sVotes;
            this.others = others;
            this.depth = depth;
        }
    }

    private static String keyOthers(long[] others) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < others.length; i++) {
            if (i > 0) sb.append(',');
            sb.append(others[i]);
        }

        return sb.toString();
    }

    private static long[] removeMinGroup(long[] others, long minVal) {
        int n = others.length;
        int idx = 0;
        while (idx < n && others[idx] == minVal) idx++;
        if (idx == 0) return others;
        return Arrays.copyOfRange(others, idx, n);
    }

    private static void generateDistributions(long totalVotes, long majorityNeed, long pool, long sVotes, long[] others, int nextDepth, ArrayDeque<State> q, HashMap<String, Long> bestForOthers) {
        int k = others.length;
        if (k == 0) {
            long sNew = sVotes + pool;
            q.add(new State(sNew, others, nextDepth));
            return;
        }

        long max = others[k - 1];
        HashSet<Long> targetsSet = new HashSet<>();
        for (long v : others) {
            targetsSet.add(v);
            targetsSet.add(v + 1);
        }

        targetsSet.add(sVotes);
        targetsSet.add(sVotes + 1);
        targetsSet.add(max + 1);
        
        long[] targets = new long[targetsSet.size()];
        int ti = 0;
        for (Long v : targetsSet) targets[ti++] = v;
        Arrays.sort(targets);

        java.util.function.BiConsumer<Long, long[]> tryEnqueue = (sNewObj, oNew) -> {
            long sNew = sNewObj;
            if (sNew <= majorityNeed) {
                long minOther = oNew.length == 0 ? Long.MAX_VALUE : oNew[0];
                if (sNew <= minOther) return;
            }

            String key = keyOthers(oNew);
            Long best = bestForOthers.get(key);
            if (best != null && best >= sNew) return;
            bestForOthers.put(key, sNew);
            q.add(new State(sNew, oNew, nextDepth));
        };

        {
            long sNew = sVotes + pool;
            tryEnqueue.accept(sNew, others);
        }

        for (int p = 1; p <= k; p++) {
            for (long L : targets) {
                long cost = 0;
                boolean anyRaise = false;
                for (int i = 0; i < p; i++) {
                    if (others[i] < L) {
                        anyRaise = true;
                        long add = L - others[i];
                        cost += add;
                        if (cost > pool) break;
                    }
                }

                if (!anyRaise) continue;
                if (cost > pool) continue;

                long[] oNew = Arrays.copyOf(others, k);
                for (int i = 0; i < p; i++) {
                    if (oNew[i] < L) oNew[i] = L;
                } 

                Arrays.sort(oNew);

                long sNew = sVotes + (pool - cost);
                tryEnqueue.accept(sNew, oNew);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int c = fs.nextInt();
        long[] v = new long[c];
        long total = 0;
        for (int i = 0; i < c; i++) {
            v[i] = fs.nextLong();
            total += v[i];
        }

        long majorityNeed = total / 2;

        long maxVal = -1, secondVal = -1;
        int maxCount = 0, secondCount = 0;
        for (long x : v) maxVal = Math.max(maxVal, x);
        for (long x : v) if (x == maxVal) maxCount++;

        for (long x : v) if (x < maxVal) secondVal = Math.max(secondVal, x);
        for (long x : v) if (x == secondVal) secondCount++;

        if (secondVal < 0 || maxCount != 1 || secondCount != 1) {
            System.out.print("IMPOSSIBLE TO WIN");
            return;
        }

        if (maxVal > majorityNeed) {
            System.out.print("IMPOSSIBLE TO WIN");
            return;
        }

        int sIdx = -1;
        for (int i = 0; i < c; i++) {
            if (v[i] == secondVal) { sIdx = i; break; }
        }

        long sVotes0 = v[sIdx];

        long[] others0 = new long[c - 1];
        int oi = 0;

        for (int i = 0; i < c; i++) if (i != sIdx) others0[oi++] = v[i];
        Arrays.sort(others0);

        long minAll = Math.min(sVotes0, others0.length == 0 ? Long.MAX_VALUE : others0[0]);
        if (minAll == sVotes0) {
            System.out.print("IMPOSSIBLE TO WIN");
            return;
        }

        int t = 0;
        while (t < others0.length && others0[t] == minAll) t++;
        long pool0 = minAll * (long) t;
        long[] othersAfter = Arrays.copyOfRange(others0, t, others0.length);

        ArrayDeque<State> q = new ArrayDeque<>();
        HashMap<String, Long> bestForOthers = new HashMap<>();

        generateDistributions(total, majorityNeed, pool0, sVotes0, othersAfter, 1, q, bestForOthers);
        int answer = -1;

        while (!q.isEmpty()) {
            State cur = q.pollFirst();
            long sVotes = cur.sVotes;
            long[] others = cur.others;
            int depth = cur.depth;

            if (sVotes > majorityNeed) {
                answer = depth;
                break;
            }

            if (others.length == 0) continue;

            long minOther = others[0];
            long minAllNow = Math.min(sVotes, minOther);
            if (minAllNow == sVotes) {
                continue;
            }

            int idx = 0;
            while (idx < others.length && others[idx] == minOther) idx++;
            long pool = minOther * (long)idx;
            long[] othersNext = Arrays.copyOfRange(others, idx, others.length);
            generateDistributions(total, majorityNeed, pool, sVotes, othersNext, depth + 1, q, bestForOthers);
        }

        if (answer < 0) System.out.print("IMPOSSIBLE TO WIN");
        else System.out.print(answer);
    }
}