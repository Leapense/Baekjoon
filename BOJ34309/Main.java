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

        int N = fs.nextInt();
        String first = fs.next();
        int prevR = first.charAt(0) - 'A';
        int prevC = first.charAt(1) - 'A';

        long total = 0;

        for (int i = 1; i < N; i++) {
            String cur = fs.next();
            int r = cur.charAt(0) - 'A';
            int c = cur.charAt(1) - 'A';

            total += Math.abs(r - prevR) + Math.abs(c - prevC);

            prevR = r;
            prevC = c;
        }

        System.out.println(total);
    }
}