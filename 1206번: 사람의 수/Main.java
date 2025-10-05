import java.io.*;
import java.util.*;

public class Main {
    static int parseThousandths(String s) {
        s = s.trim();
        s = s.replace(".", "");
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();
        int N = Integer.parseInt(line.trim());
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            while (s != null && s.trim().isEmpty()) s = br.readLine();
            A[i] = parseThousandths(s);
        }

        int answer = solve(A);
        System.out.println(answer);
    }

    static int solve(int[] A) {
        outer:
        for (int M = 1; M <= 1000; M++) {
            for (int a : A) {
                long left = ((long)a + 0L) * M;
                long right = ((long)a + 1L) * M;

                long L = (left + 999) / 1000;
                long R = (right + 999) / 1000;

                if (L >= R) {
                    continue outer;
                }
            }
            return M;
        }

        return 1000;
    }
}