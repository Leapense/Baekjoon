import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = System.in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        private int skipSpaces() throws IOException {
            int c;
            while ((c = readByte()) != -1 && c <= ' ') { }
            return c;
        }

        String next() throws IOException {
            int c = skipSpaces();
            if (c == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
                if (c == -1) break;
            }

            return sb.toString();
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
                if (c == -1) break;
            }

            return val * sign;
        }
    }

    private static int typeIndex(String type) {
        char c = type.charAt(0);
        if (c == 'S') return 0;
        if (c == 'F') return 1;
        return 2;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new BufferedInputStream(System.in));
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int[] cnt = new int[3];

        for (int i = 0; i < N; i++) {
            String type = fs.next();
            String dir = fs.next();
            int k = fs.nextInt();

            int idx = typeIndex(type);
            if (dir.charAt(0) == 'I') {
                cnt[idx] += k;
            } else {
                cnt[idx] -= k;
            }
        }

        int total = cnt[0] + cnt[1] + cnt[2];
        if (total == 0) {
            System.out.print("NO STRAGGLERS");
        } else {
            System.out.print(total);
        }
    }
}