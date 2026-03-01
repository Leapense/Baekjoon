import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static String firstSyllable(String s) {
        int n = s.length();
        int firstVowel = -1;

        for (int i = 0; i < n; i++) {
            if (isVowel(s.charAt(i))) {
                firstVowel = i;
                break;
            }
        }

        if (firstVowel == -1) return null;

        int firstConsonantAfter = -1;
        for (int i = firstVowel + 1; i < n; i++) {
            if (!isVowel(s.charAt(i))) {
                firstConsonantAfter = i;
                break;
            }
        }

        if (firstConsonantAfter == -1) return null;

        return s.substring(0, firstConsonantAfter);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();

        String sa = firstSyllable(a);
        String sb = firstSyllable(b);

        if (sa == null || sb == null) {
            System.out.print("no such exercise");
        } else {
            System.out.print(sa + sb);
        }
    }
}