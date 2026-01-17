import java.io.*;
import java.util.*;

public class Main {

    private static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0,
            len = 0;

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

        int nextInt() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }

            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int Q = fs.nextInt();

        for (int i = 0; i < Q; i++) {
            int level = fs.nextInt();
            int lang = fs.nextInt();
            int read = fs.nextInt();
            int listen = fs.nextInt();

            if (level != 1 && level != 2) {
                sb.append("NO\n");
                continue;
            }

            if (listen < 50) {
                sb.append("NO\n");
                continue;
            }

            int S = (level == 1) ? 100 : 90;
            boolean lang31 = (3 * lang) < S;
            boolean read31 = (3 * read) < S;
            boolean cond3_1 = lang31 && read31;

            boolean lang32 = lang <= 21;
            boolean read32 = read <= 21;
            boolean cond3_2 = lang32 || read32;

            if (cond3_1 || cond3_2) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.print(sb.toString());
    }
}
