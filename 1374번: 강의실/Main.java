import java.io.*;
import java.util.*;

public class Main {
    static class Interval {
        int s, e;
        Interval(int s, int e) { this.s = s; this.e = e; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        Interval[] arr = new Interval[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[i] = new Interval(s, e);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.s != b.s) return Integer.compare(a.s, b.s);
            return Integer.compare(a.e, b.e);
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;

        for (Interval iv : arr) {
            if (!pq.isEmpty() && pq.peek() <= iv.s) {
                pq.poll();
            }
            pq.add(iv.e);
            if (pq.size() > answer) answer = pq.size();
        }

        System.out.println(answer);
    }
}