import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

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

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }

            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int half = N / 2;

        int[] firstHalf = new int[half];
        for (int i = 0; i < half; i++) {
            firstHalf[i] = fs.nextInt();
        }

        long count = 0;
        for (int i = 0; i < half; i++) {
            int x = fs.nextInt();
            if (x == firstHalf[i]) count += 2;
        }

        System.out.print(count);
    }
}