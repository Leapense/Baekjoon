import java.io.*;
import java.util.*;

public class Main {
    static int C, P;
    static int[] h;

    static int check(String pattern) {
        int n = pattern.length();
        int ret = 0;
        for (int i = 0; i + n <= C; i++) {
            boolean ok = true;
            int base = h[i] - (pattern.charAt(0) - '0');
            for (int j = 0; j < n; j++) {
                if (h[i + j] - (pattern.charAt(j) - '0') != base) {
                    ok = false;
                    break;
                }
            }
            if (ok) ret++;
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        h = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        int ret = 0;
        if (P == 1) ret = check("0") + check("0000");
        if (P == 2) ret = check("00");
        if (P == 3) ret = check("001") + check("10");
        if (P == 4) ret = check("100") + check("01");
        if (P == 5) ret = check("000") + check("01") + check("101") + check("10");
        if (P == 6) ret = check("000") + check("00") + check("011") + check("20");
        if (P == 7) ret = check("000") + check("00") + check("110") + check("02");

        System.out.println(ret);
    }
}