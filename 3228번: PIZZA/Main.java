import java.io.*;
import java.util.*;

public class Main {
    static int K, R, M, N;
    static int[] locX, locY;
    static int[] solX, solY, solS;
    static BitSet[] cover;
    static int totalPeople;
    static int best;
    static int C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        long R2 = 1L * R * R;

        M = Integer.parseInt(br.readLine().trim());
        C = M;
        locX = new int[M];
        locY = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            locX[i] = Integer.parseInt(st.nextToken());
            locY[i] = Integer.parseInt(st.nextToken());
        }

        N = Integer.parseInt(br.readLine().trim());
        solX = new int[N];
        solY = new int[N];
        solS = new int[N];
        totalPeople = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            solX[i] = Integer.parseInt(st.nextToken());
            solY[i] = Integer.parseInt(st.nextToken());
            solS[i] = Integer.parseInt(st.nextToken());
            totalPeople += solS[i];
        }

        cover = new BitSet[M];
        for (int i = 0; i < M; i++) {
            BitSet bs = new BitSet(N);
            for (int j = 0; j < N; j++) {
                long dx = locX[i] - solX[j];
                long dy = locY[i] - solY[j];
                long d2 = dx * dx + dy * dy;
                if (d2 <= R2) {
                    bs.set(j);
                }
            }
            cover[i] = bs;
        }

        best = 0;
        dfs(0, 0, new BitSet(N), 0);
        System.out.println(best);
    }

    static void dfs(int start, int chosen, BitSet union, int sum) {
        if (sum > best) best = sum;
        if (chosen == K || start == M) return;

        for (int i = start; i < M; i++) {
            BitSet newCovered = (BitSet)cover[i].clone();
            newCovered.andNot(union);

            int add = weightOf(newCovered);
            if (add == 0 && chosen + 1 <= K) {

            }
            BitSet nextUnion = (BitSet)union.clone();
            nextUnion.or(cover[i]);
            dfs(i + 1, chosen + 1, nextUnion, sum + add);
        }
    }

    static int weightOf(BitSet bs) {
        int s = 0;
        for (int idx = bs.nextSetBit(0); idx >= 0; idx = bs.nextSetBit(idx + 1)) {
            s += solS[idx];
        }

        return s;
    }
}