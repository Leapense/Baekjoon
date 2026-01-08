import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        HashSet<String> set = new HashSet<>(N * 2);

        for (int i = 0; i < N; i++) {
            set.add(fs.next());
        }

        for (int i = 0; i < N - 1; i++) {
            set.remove(fs.next());
        }

        String missing = null;
        for (String s : set) {
            missing = s;
            break;
        }

        System.out.print(missing);
    }

    static class FastScanner {

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

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = readByte();
                if (c == -1) return null;
            } while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
                if (c == -1) break;
            }

            return sb.toString();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
