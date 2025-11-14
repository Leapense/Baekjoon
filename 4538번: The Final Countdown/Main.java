import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) {
            line = br.readLine();
        }
        int N = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < N; tc++) {
            String lStr;
            while ((lStr = br.readLine()) != null && lStr.trim().isEmpty()) {

            }
            int L = Integer.parseInt(lStr.trim());
            List<String> commands = new ArrayList<>();
            for (int i = 0; i < L; i++) {
                commands.add(br.readLine().trim());
            }

            int startT = -1;
            for (String cmd : commands) {
                String[] tokens = cmd.split(" ");
                int t = Integer.parseInt(tokens[0]);
                String directive = tokens[1];
                if ("START".equals(directive)) {
                    startT = t;
                    break;
                }
            }

            int unconditionalSum = 0;

            Map<String, Integer> trueSum = new HashMap<>();
            Map<String, Integer> falseSum = new HashMap<>();

            for (String cmd : commands) {
                String[] tokens = cmd.split(" ");
                int t = Integer.parseInt(tokens[0]);
                String directive = tokens[1];

                if ("START".equals(directive)) {
                    continue;
                }

                if (t > startT) {
                    continue;
                }

                int n = Integer.parseInt(tokens[2]);
                if (tokens.length == 3) {
                    unconditionalSum += n;
                } else {
                    if ("IF".equals(tokens[3])) {
                        if (tokens.length == 5) {
                            String cond = tokens[4];
                            trueSum.put(cond, trueSum.getOrDefault(cond, 0) + n);
                        } else if (tokens.length == 6 && "NOT".equals(tokens[4])) {
                            String cond = tokens[5];
                            falseSum.put(cond, falseSum.getOrDefault(cond, 0) + n);
                        }
                    }
                }
            }

            int baseTime = startT + unconditionalSum;

            Set<String> condNames = new HashSet<>();
            condNames.addAll(trueSum.keySet());
            condNames.addAll(falseSum.keySet());

            int minExtra = 0;
            int maxExtra = 0;

            for (String cond : condNames) {
                int tSum = trueSum.getOrDefault(cond, 0);
                int fSum = falseSum.getOrDefault(cond, 0);
                minExtra += Math.min(tSum, fSum);
                maxExtra += Math.max(tSum, fSum);
            }

            int minTotal = baseTime + minExtra;
            int maxTotal = baseTime + maxExtra;

            sb.append(minTotal).append(" TO ").append(maxTotal).append('\n');
        }

        System.out.print(sb.toString());
    }
}