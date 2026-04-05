import java.io.*;
import java.util.*;

public class Main {
    private static final int[] SCORE = new int[26];

    static {
        put("AEILNORSTU", 1);
        put("DG", 2);
        put("BCMP", 3);
        put("FHVWY", 4);
        put("K", 5);
        put("JX", 8);
        put("QZ", 10);
    }

    private static void put(String letters, int value) {
        for (int i = 0; i < letters.length(); i++) {
            SCORE[letters.charAt(i) - 'A'] = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        int[] tray = new int[26];
        int blanks = 0;
        for (int i = 0; i < T; i++) {
            String s = br.readLine().trim();
            char ch = s.charAt(0);
            if (ch == '#') {
                blanks++;
            } else {
                tray[ch - 'A']++;
            }
        }

        String bestWord = null;
        int bestScore = -1;

        BufferedReader dictReader = openDictionary();
        String word;

        while ((word = dictReader.readLine()) != null) {
            word = word.trim();
            if (word.isEmpty()) continue;
            if (word.length() > T) continue;

            int[] need = new int[26];
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch < 'A' || ch > 'Z') {
                    ch = Character.toUpperCase(ch);
                }
                need[ch - 'A']++;
            }

            int requiredBlanks = 0;
            int score = 0;
            for (int i = 0; i < 26; i++) {
                int useReal = Math.min(need[i], tray[i]);
                score += useReal * SCORE[i];
                if (need[i] > tray[i]) {
                    requiredBlanks += (need[i] - tray[i]);
                }
            }

            if (requiredBlanks <= blanks) {
                if (score > bestScore || (score == bestScore && (bestWord == null || word.compareTo(bestWord) < 0))) {
                    bestScore = score;
                    bestWord = word;
                }
            }
        }

        dictReader.close();
        System.out.println(bestWord);
    }

    private static BufferedReader openDictionary() throws IOException {
        File file = new File("dict.txt");
        if (file.exists()) {
            return new BufferedReader(new FileReader(file));
        }

        InputStream is = Main.class.getResourceAsStream("/dict.txt");
        if (is != null) {
            return new BufferedReader(new InputStreamReader(is));
        }

        throw new FileNotFoundException("dict.txt 파일을 찾을 수 없습니다.");
    }
}