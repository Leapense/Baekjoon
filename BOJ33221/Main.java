import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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

        private int skipSpaces() throws IOException {
            int c;
            while ((c = readByte()) != -1) {
                if (c > ' ') return c;
            }
            return -1;
        }

        int nextInt() throws IOException {
            int c = skipSpaces();
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

        long nextResult() throws IOException {
            int c = skipSpaces();
            if (c == 'X') {
                int d;
                while ((d = readByte()) != -1 && d > ' ') {

                }

                return -1L;
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(new BufferedInputStream(System.in));

        int t = fs.nextInt();
        int p = fs.nextInt();

        int profSolved = 0;
        long profSum = 0;
        for (int j = 0; j < p; j++) {
            long v = fs.nextResult();
            if (v != -1) {
                profSolved++;
                profSum += v;
            }
        }

        int cakes = 0;
        for (int i = 1; i < t; i++) {
            int solved = 0;
            long sum = 0;
            for (int j = 0; j < p; j++) {
                long v = fs.nextResult();
                if (v != -1) {
                    solved++;
                    sum += v;
                }
            }

            if (solved > profSolved || (solved == profSolved && sum <= profSum)) {
                cakes++;
            }
        }

        System.out.println(cakes);
    }
}