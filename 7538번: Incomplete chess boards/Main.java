import java.io.InputStream;
import java.io.IOException;

public class Main {
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
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
                c = read();
            } while (c <= ' ' && c != -1);

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int k = fs.nextInt();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= k; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int c = fs.nextInt();
            int d = fs.nextInt();

            int color1 = (a + b) & 1;
            int color2 = (c + d) & 1;

            int ans = (color1 != color2) ? 1 : 0;
            sb.append("Scenario #").append(i).append(":\n");
            sb.append(ans).append("\n\n");
        }

        System.out.print(sb.toString());
    }
}