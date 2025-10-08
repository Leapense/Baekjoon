import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean allNines(char[] a) {
        for (char c : a) if (c != '9') return false;
        return true;
    }

    private static String nextPalindrome(String s) {
        int n = s.length();
        char[] b = s.toCharArray();

        if (allNines(b)) {
            StringBuilder sb = new StringBuilder(n + 1);
            sb.append('1');
            for (int i = 0; i < n - 1; i++) sb.append('0');
            sb.append('1');
            return sb.toString();
        }

        for (int i = 0; i < n / 2; i++) {
            b[n - 1 - i] = b[i];
        }
        String mirrored = new String(b);
        if (mirrored.compareTo(s) > 0) {
            return mirrored;
        }

        int i = (n - 1) / 2;
        int j = n / 2;
        while (i >= 0 && b[i] == '9') {
            b[i] = '0';
            b[j] = '0';
            i--;
            j++;
        }

        if (i >= 0) {
            b[i] = (char)(b[i] + 1);
            b[j] = b[i];
            return new String(b);
        } else {
            StringBuilder sb = new StringBuilder(n + 1);
            sb.append('1');
            for (int k = 0; k < n - 1; k++) sb.append('0');
            sb.append('1');
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        System.out.println(nextPalindrome(s));
    }
}