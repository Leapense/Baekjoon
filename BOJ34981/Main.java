import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int cur = X * 60 + Y;
        int n = Integer.parseInt(br.readLine());

        int minWait = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int start = x * 60 + y;
            int wait;

            if (cur <= start) {
                wait = start - cur;
            } else {
                int diff = cur - start;
                int k = (diff + d - 1) / d;
                int next = start + k * d;
                
                if (next <= 23 * 60 + 59) {
                    wait = next - cur;
                } else {
                    wait = (24 * 60 - cur) + start;
                }
            }

            minWait = Math.min(minWait, wait);
        }

        int arrival = (cur + minWait) % (24 * 60);
        int hh = arrival / 60;
        int mm = arrival % 60;

        System.out.printf("%02d:%02d", hh, mm);
        br.close();
    }
}