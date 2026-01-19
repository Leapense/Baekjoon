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

        int nextInt() throws IOException {
            int c;

            do {
                c = readByte();
                if (c == -1) return Integer.MIN_VALUE;
            } while (c <= ' ');

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

        while (true) {
            int R = fs.nextInt();
            if (R == Integer.MIN_VALUE) break;
            if (R == 0) break;

            int[] M = new int[R];
            int[] L = new int[R];

            int scoreM = 0, scoreL = 0;
            for (int i = 0; i < R; i++) {
                M[i] = fs.nextInt();
                scoreM += M[i];
            }
            for (int i = 0; i < R; i++) {
                L[i] = fs.nextInt();
                scoreL += L[i];
            }

            boolean bonusResolved = false;
            for (int i = 2; i < R && !bonusResolved; i++) {
                boolean markTriple = (M[i] == M[i - 1] && M[i - 1] == M[i - 2]);
                boolean letiTriple = (L[i] == L[i - 1] && L[i - 1] == L[i - 2]);

                if (markTriple || letiTriple) {
                    if (markTriple ^ letiTriple) {
                        if (markTriple) scoreM += 30;
                        else scoreL += 30;
                    }

                    bonusResolved = true;
                }
            }

            if (scoreM > scoreL) sb.append('M');
            else if (scoreM < scoreL) sb.append('L');
            else sb.append('T');
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}