import java.io.*;
import java.util.*;

public class Main {
    private static class FastScanner {
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
        String oldPwd = fs.next();
        String newPwd = fs.next();

        int[] oldCount = new int[26];
        int[] newCount = new int[26];

        for (int i = 0; i < oldPwd.length(); i++) {
            oldCount[oldPwd.charAt(i) - 'a']++;
        }
        for (int i = 0; i < newPwd.length(); i++) {
            newCount[newPwd.charAt(i) - 'a']++;
        }

        long ans = 0;
        for (int i = 0; i < 26; i++) {
            int diff = newCount[i] - oldCount[i];
            if (diff > 0) ans += diff;
        }

        System.out.println(ans);
    }
}