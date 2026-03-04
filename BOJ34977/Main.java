import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] L = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            L[i] = Integer.parseInt(st.nextToken());

        int maxK = N / 2;
        for (int k = 1; k <= maxK; k++) {
            boolean ok = true;
            for (int i = 0; i < k; i++) {
                if (L[i] != L[N - k + i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.print("yes");
                return;
            }
        }
        System.out.print("no");
    }
}