import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static long pow(long v, int x) {
        if (x == 1) return v;
        if (x == 2) return v * v;
        return v * v * v;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long d = Long.parseLong(st.nextToken());

        int answer = -1;
        int count = 0;

        for (int x = 1; x <= 3; x++) {
            long sum = pow(a, x) + pow(b, x) + pow(c, x);
            if (sum == d) {
                count++;
                answer = x;
            }
        }

        if (count == 1) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}