import java.io.*;
import java.util.*;

public class Main {
    private static int nextHour(int h) {
        return (h == 12) ? 1 : (h + 1);
    }

    private static String formula(int k) {
        if (k == 0) return "XX";
        if (k > 0) return "XX + " + k;
        return "XX - " + (-k);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int SH = Integer.parseInt(st.nextToken());
            int SM = Integer.parseInt(st.nextToken());
            int DH = Integer.parseInt(st.nextToken());
            int DM = Integer.parseInt(st.nextToken());
            int D = DH * 60 + DM;
            int rows = (SM + D) / 60 + 1;
            
            out.append("------+---------\n");
            out.append(" time | elapsed\n");
            out.append("------+---------\n");

            int hour = SH;
            for (int t = 0; t < rows; t++) {
                int k = t * 60 - SM;
                out.append(String.format("%2d:XX | %s\n", hour, formula(k)));
                hour = nextHour(hour);
            }
        }

        System.out.print(out.toString());
    }
}