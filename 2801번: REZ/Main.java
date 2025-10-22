import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine().trim();
        long K = Long.parseLong(line);

        if (K <= 1) {
            System.out.println(0);
            return;
        }

        long t = K - 1;
        double approx = (Math.sqrt(1.0 + 8.0 * t) - 1.0) / 2.0;
        long N = (long) Math.ceil(approx);

        while(N * (N + 1) / 2 < t) N++;
        while (N > 0 && (N - 1) * N / 2 >= t) N--;

        System.out.println(N);
        if (N == 0) return;

        int step = 10000 / (int) (N + 1);
        if (step == 0) step = 1;

        int[] topX = new int[(int)N];
        int[] botX = new int[(int)N];

        for (int i = 0; i < N; i++) {
            topX[i] = -5000 + (i + 1) * step;
            botX[i] = -5000 + (i + 1) * step;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int x1 = topX[i];
            int y1 = 5000;
            int x2 = botX[(int)N - 1 - i];
            int y2 = -5000;

            sb.append(x1).append(' ').append(y1).append(' ').append(x2).append(' ').append(y2).append('\n');
        }

        System.out.print(sb.toString());
    }
}