import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= K; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[] g = new int[d + 1];
            int[] s = new int[d + 1];

            for (int i = 1; i <= d; i++) {
                st = new StringTokenizer(br.readLine());
                g[i] = Integer.parseInt(st.nextToken());
                s[i] = Integer.parseInt(st.nextToken());
            }

            int[] gainStart = new int[d + 5];
            int[] soreDiff = new int[d + 5];

            int permanentGain = 0;
            int activeSoreness = 0;
            int failDay = -1;

            for (int day = 1; day <= d; day++) {
                permanentGain += gainStart[day];
                activeSoreness += soreDiff[day];

                int currentStrength = w + permanentGain - activeSoreness;
                int calfWeight = c + day - 1;
                if (currentStrength < calfWeight) {
                    failDay = day;
                    break;
                }

                if (day + 3 <= d) {
                    gainStart[day + 3] += g[day];
                }
                if (day + 1 <= d) {
                    soreDiff[day + 1] += s[day];
                }
                if (day + 3 <= d) {
                    soreDiff[day + 3] -= s[day];
                }
            }

            sb.append("Data Set ").append(tc).append(":\n");
            if (failDay == -1) {
                sb.append("Completed successfully.\n");
            } else {
                sb.append(failDay).append("\n");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}