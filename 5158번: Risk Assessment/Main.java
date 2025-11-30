import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        int K = Integer.parseInt(line.trim());
        StringBuilder output = new StringBuilder();
        for (int dataSet = 1; dataSet <= K; dataSet++) {
            StringTokenizer st;
            do {
                line = br.readLine();
            } while (line != null && line.trim().isEmpty());

            st = new StringTokenizer(line);
            int C = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            String[] companies = new String[C];
            Map<String, Integer> companyIndex = new HashMap<String, Integer>();
            for (int i = 0; i < C; i++) {
                String name = br.readLine();
                companies[i] = name;
                companyIndex.put(name, i);
            }

            Map<String, Double> qualityScore = new HashMap<String, Double>();
            for (int i = 0; i < Q; i++) {
                line = br.readLine();
                st = new StringTokenizer(line);
                String w = st.nextToken();
                double val = Double.parseDouble(st.nextToken());
                qualityScore.put(w.toLowerCase(Locale.ROOT), val);
            }
            StringBuilder textBuilder = new StringBuilder();
            for (int i = 0; i < L; i++) {
                line = br.readLine();
                if (i > 0) {
                    textBuilder.append('\n');
                }
                textBuilder.append(line);
            }
            String text = textBuilder.toString();
            double[] totals = processOneDataSet(C, companyIndex, qualityScore, text);

            output.append("Data Set ").append(dataSet).append(":\n");
            for (int i = 0; i < C; i++) {
                double v = totals[i];
                if (Math.abs(v) < 1e-9) {
                    v = 0.0;
                }
                output.append(String.format("%.2f\n", v));
            }

            output.append("\n");
        }

        System.out.print(output.toString());
    }

    private static double[] processOneDataSet(int C, Map<String, Integer> companyIndex, Map<String, Double> qualityScore, String text) {
        double[] totals = new double[C];
        double[] sentenceBase = new double[C];

        boolean hasNot = false;
        int countVery = 0;
        int countExtremely = 0;
        int countSlightly = 0;

        int currentCompany = -1;
        StringBuilder word = new StringBuilder();
        int len = text.length();
        for (int pos = 0; pos <= len; pos++) {
            char ch = (pos < len) ? text.charAt(pos) : '.';
            if (isWordChar(ch)) {
                word.append(ch);
            } else {
                if (word.length() > 0) {
                    String w = word.toString();
                    word.setLength(0);

                    Integer idx = companyIndex.get(w);
                    if (idx != null) {
                        currentCompany = idx.intValue();
                    } else {
                        String lw = w.toLowerCase(Locale.ROOT);
                        if ("not".equals(lw)) {
                            hasNot = true;
                        } else if ("very".equals(lw)) {
                            countVery++;
                        } else if ("extremely".equals(lw)) {
                            countExtremely++;
                        } else if ("slightly".equals(lw)) {
                            countSlightly++;
                        } else {
                            Double scoreObj = qualityScore.get(lw);
                            if (scoreObj != null && currentCompany >= 0) {
                                sentenceBase[currentCompany] += scoreObj.doubleValue();
                            }
                        }
                    }
                }

                if (ch == '.') {
                    if (!hasNot) {
                        double mult = 1.0;
                        if (countVery > 0) {
                            mult *= Math.pow(2.0, countVery);
                        } 
                        if (countExtremely > 0) {
                            mult *= Math.pow(3.0, countExtremely);
                        }
                        if (countSlightly > 0) {
                            mult *= Math.pow(0.5, countSlightly);
                        }

                        for (int i = 0; i < C; i++) {
                            if (sentenceBase[i] != 0.0) {
                                totals[i] += sentenceBase[i] * mult;
                            }
                        }
                    }

                    Arrays.fill(sentenceBase, 0.0);
                    hasNot = false;
                    countVery = 0;
                    countExtremely = 0;
                    countSlightly = 0;
                }
            }
        }

        return totals;
    }

    private static boolean isWordChar(char ch) {
        return (ch >= 'A' && ch <= 'Z') ||
               (ch >= 'a' && ch <= 'z') ||
               ch == '-';
    }
}