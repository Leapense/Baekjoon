import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        if (N % 2 == 0) {
            int K = N / 2;
            sb.append(K).append('\n');
            for (int i = 0; i < K; i++) {
                if (i > 0)
                    sb.append(' ');
                sb.append(2);
            }
            sb.append('\n');
        } else {
            // N is odd, N >= 3 always holds because N >= 2 and odd => >= 3
            int twos = (N - 3) / 2;
            int K = 1 + twos;
            sb.append(K).append('\n');
            sb.append(3);
            for (int i = 0; i < twos; i++) {
                sb.append(' ').append(2);
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}