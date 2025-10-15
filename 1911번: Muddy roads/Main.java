import java.io.*;
import java.util.*;

public class Main {
    static class Pool {
        long s, e;
        Pool(long s, long e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        Pool[] pools = new Pool[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            pools[i] = new Pool(s, e);
        }

        Arrays.sort(pools, Comparator.comparingLong(a -> a.s));

        long right = 0;
        long answer = 0;

        for (Pool p : pools) {
            if (p.e <= right) continue;
            long start = Math.max(p.s, right);
            long uncovered = p.e - start;
            long planks = (uncovered + L - 1) / L;
            answer += planks;
            right = start + planks * L;
        }

        System.out.println(answer);
    }
}