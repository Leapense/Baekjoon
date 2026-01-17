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

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) return Long.MIN_VALUE;
            } while (c <= ' ');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }

            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int M = fs.nextInt();

        int[] maxOfClass = new int[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int g = fs.nextInt();
                if (g > maxOfClass[j]) maxOfClass[j] = g;
            }
        }

        long answer = 0;
        for (int j = 0; j < M; j++) {
            answer += maxOfClass[j];
        }

        System.out.println(answer);
    }
}
