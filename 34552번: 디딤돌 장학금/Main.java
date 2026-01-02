import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int gpaToInt(String s) {
        return Integer.parseInt(s.replace(".", ""));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] M = new int[11];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= 10; i++) {
            M[i] = Integer.parseInt(st.nextToken());
        }

        int N = Integer.parseInt(br.readLine().trim());

        long sum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken());
            String L = st.nextToken();
            int S = Integer.parseInt(st.nextToken());

            int gpa = gpaToInt(L);
            if (S >= 17 && gpa >= 200) {
                sum += M[B];
            }
        }

        System.out.println(sum);
    }
}