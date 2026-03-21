import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        int[] minPizza = new int[24];
        Arrays.fill(minPizza, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if (p < minPizza[t]) {
                minPizza[t] = p;
            }
        }

        List<Integer> candidates = new ArrayList<>();
        for (int t = 0; t < 24; t++) {
            if (minPizza[t] != Integer.MAX_VALUE) {
                candidates.add(minPizza[t]);
            }
        }

        Collections.sort(candidates);

        int answer = 0;
        long sum = 0;
        for (int pizza : candidates) {
            if (sum + pizza <= c) {
                sum += pizza;
                answer++;
            } else {
                break;
            }
        }

        System.out.print(answer);
    }
}