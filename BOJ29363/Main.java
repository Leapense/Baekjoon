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
                if (len <= 0)
                    return -1;
            }

            return buffer[ptr++];
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1)
                    return null;
            } while (c <= ' ');

            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
                if (c == -1)
                    break;
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            String s = next();
            return Integer.parseInt(s);
        }
    }

    private static int lcp(String a, String b) {
        int m = Math.min(a.length(), b.length());
        for (int i = 0; i < m; i++) {
            if (a.charAt(i) != b.charAt(i))
                return i;
        }

        return m;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        String prev = fs.next();

        long answer = prev.length();
        answer += (long) (n - 1);

        for (int i = 2; i <= n; i++) {
            String cur = fs.next();
            int l = lcp(prev, cur);
            int lenPrev = prev.length();
            int lenCur = cur.length();

            int typeCost = lenCur;
            int copyCost = 1 + (lenPrev - l) + (lenCur - l);

            answer += Math.min(typeCost, copyCost);
            prev = cur;
        }

        System.out.println(answer);
    }
}