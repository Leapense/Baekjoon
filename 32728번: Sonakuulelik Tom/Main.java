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
            String s = next();
            if (s == null) return 0;
            return Integer.parseInt(s);
        }
    }

    private static int startBox(char ch) {
        if (ch == 's') return 0;
        if (ch == 'r') return 1;
        return 2;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        int K = fs.nextInt();

        String seq = (N == 0) ? "" : fs.next();
        if (seq == null) seq = "";

        StringBuilder[] box = new StringBuilder[3];
        for (int i = 0; i < 3; i++) box[i] = new StringBuilder();
        int[] cnt = new int[3];

        for (int i = 0; i < N; i++) {
            char ch = seq.charAt(i);
            int start = startBox(ch);

            for (int t = 0; t < 3; t++) {
                int b = (start + t) % 3;
                if (cnt[b] < K) {
                    box[b].append(ch);
                    cnt[b]++;
                    break;
                }
            } 
        }

        StringBuilder out = new StringBuilder();
        out.append(box[0]).append('\n');
        out.append(box[1]).append('\n');
        out.append(box[2]).append('\n');
        System.out.print(out.toString());
    }
}