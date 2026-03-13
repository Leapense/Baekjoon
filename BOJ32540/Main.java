import java.io.*;
import java.util.*;

public class Main {
    private static final char[] LETTER_TO_DIGIT = new char[26];
    static {
        fill("abc", '2');
        fill("def", '3');
        fill("ghi", '4');
        fill("jkl", '5');
        fill("mno", '6');
        fill("pqrs", '7');
        fill("tuv", '8');
        fill("wxyz", '9');
    }

    private static void fill(String letters, char digit) {
        for (int i = 0; i < letters.length(); i++) {
            LETTER_TO_DIGIT[letters.charAt(i) - 'a'] = digit;
        }
    }

    private static String toDigit(String word) {
        char[] digits = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            digits[i] = LETTER_TO_DIGIT[word.charAt(i) - 'a'];
        }

        return new String(digits);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = fs.next();
            String digits = toDigit(word);
            map.computeIfAbsent(digits, k -> new ArrayList<>()).add(word);
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String phone = fs.next();
            List<String> words = map.get(phone);

            if (words == null) {
                out.append(0).append('\n');
            } else {
                out.append(words.size());
                for (String word : words) {
                    out.append(' ').append(word);
                }
                out.append('\n');
            }
        }

        System.out.print(out);
    }

    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream is) {
            this.in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }

            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            do {
                c = read();
            } while (c != -1 && c <= ' ');

            while (c != -1 && c > ' ') {
                sb.append((char) c);
                c = read();
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}