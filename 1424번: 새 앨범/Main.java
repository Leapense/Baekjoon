import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine().trim());
        long L = Long.parseLong(br.readLine().trim());
        long C = Long.parseLong(br.readLine().trim());

        long sMax = (C + 1) / (L + 1);
        long s = (sMax % 13 == 0) ? (sMax - 1) : sMax;
        if (s <= 0) s = 1;

        long m = N / s;
        long r = N % s;

        long ans;
        if (r == 0) {
            ans = m;
        } else if (r % 13 != 0) {
            ans = m + 1;
        } else {
            if (m == 0) {
                ans = 2;
            } else {
                if ((s % 13 == 1) && (r == s - 1)) {
                    ans = m + 2;
                } else {
                    ans = m + 1;
                }
            }
        }

        System.out.println(ans);
    }
}