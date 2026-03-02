import java.io.*;
import java.util.*;

public class Main {
    private static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return null;
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
                if (c == -1) break;
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            String s = next();
            return Integer.parseInt(s);
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();

        char[] map = new char[26];
        fill(map, "ABC", '2');
        fill(map, "DEF", '3');
        fill(map, "GHI", '4');
        fill(map, "JKL", '5');
        fill(map, "MNO", '6');
        fill(map, "PQRS", '7');
        fill(map, "TUV", '8');
        fill(map, "WXYZ", '9');

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String word = fs.next();
            StringBuilder sb = new StringBuilder(word.length());
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                ch = Character.toUpperCase(ch);
                sb.append(map[ch - 'A']);
            }
            out.append(sb).append('\n');
        }

        System.out.print(out);
    }

    private static void fill(char[] map, String letters, char digit) {
        for (int i = 0; i < letters.length(); i++) {
            map[letters.charAt(i) - 'A'] = digit;
        }
    }
}