import java.io.*;
import java.util.*;

public class Main {
    static boolean isSquare(long x) {
        long r = (long) Math.sqrt(x);
        for (long d = -1; d <= 1; d++) {
            long y = r + d;
            if (y >= 0 && y * y == x) {
                return true;
            }
        }

        return false;
    }

    static long reverse(long n) {
        long rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < T; tc++) {
            long n = Long.parseLong(br.readLine().trim());
            long rev = reverse(n);

            if (isSquare(n) && isSquare(rev)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb.toString());
    }
}