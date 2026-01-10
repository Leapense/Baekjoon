import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int N = Integer.parseInt(br.readLine().trim());

        int maxT = Integer.MIN_VALUE;
        int minB = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (T > maxT) maxT = T;
            if (B < minB) minB = B;
        }

        long product = (long) maxT * (long) minB;
        int r = (int) (product % 7);

        int answer = r + 1;
        System.out.println(answer);
    }
}
