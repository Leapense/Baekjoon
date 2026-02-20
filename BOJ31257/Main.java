import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        if (!fs.hasMoreTokens()) return;
        int N = fs.nextInt();
        String s = fs.next();

        if (s == null) s = "";

        String r = new StringBuilder(s).reverse().toString();
        String t = r + "#" + s;
        int[] pi = prefixFunction(t);
        int maxPalinSuffixLen = pi[t.length() - 1];

        String append = r.substring(maxPalinSuffixLen);
        out.println(append.length());
        if (!append.isEmpty()) {
            out.println(append);
        }

        out.flush();
    }

    static int[] prefixFunction(String s) {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        public FastScanner(InputStream in) {
            this.in = in;
        }

        private boolean hasNextByte() {
            if (ptr < buflen) return true;
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buflen > 0;
        }

        private int readByte() {
            if (hasNextByte()) return buffer[ptr++];
            else return -1;
        }

        private static boolean isPrintableChar(int c) {
            return c > 32 && c < 127;
        }

        public boolean hasMoreTokens() {
            while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return hasNextByte();
        }

        public String next() {
            if (!hasMoreTokens()) throw new NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while (isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public int nextInt() {
            if (!hasMoreTokens()) throw new NoSuchElementException();
            int n = 0;
            boolean negative = false;
            int b = readByte();
            if (b == '-') {
                negative = true;
                b = readByte();
            }
            if (b < '0' || b > '9') throw new NumberFormatException();
            while (true) {
                if (b >= '0' && b <= '9') {
                    n = n * 10 + (b - '0');
                } else if (b == -1 || !isPrintableChar(b)) {
                    return negative ? -n : n;
                } else {
                    throw new NumberFormatException();
                }
                b = readByte();
            }
        }
    }
}