import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        long sum = 0;
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            if (line.equals("#")) {
                break;
            }
            if (line.equals("0")) {
                out.append(sum).append('\n');
                sum = 0;
                continue;
            }

            StringTokenizer st = new StringTokenizer(line);
            st.nextToken();
            st.nextToken();

            long actual = Long.parseLong(st.nextToken());
            String code = st.nextToken();

            long earned;
            if (code.equals("Y")) {
                earned = Math.max(actual, 500);
            } else if (code.equals("B")) {
                long bonus = (actual + 1) / 2;
                earned = actual + bonus;
            } else {
                earned = actual * 2;
            }

            sum += earned;
        }

        System.out.print(out.toString());
    }
}