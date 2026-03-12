import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
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
            } while (c <= ' ');

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

    private static String rect(int x1, int y1, int x2, int y2) {
        return x1 + " " + y1 + " " + x2 + " " + y2;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int X = fs.nextInt();
        int Y = fs.nextInt();

        int A1 = fs.nextInt();
        int B1 = fs.nextInt();

        int A2 = fs.nextInt();
        int B2 = fs.nextInt();

        int[][] board1 = {{A1, B1}, {B1, A1}};
        int[][] board2 = {{A2, B2}, {B2, A2}};

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            int w = board1[i][0];
            int h = board1[i][1];
            if (w == X && h == Y) {
                out.append(rect(0, 0, X, Y)).append('\n');
                out.append("Z\n");
                System.out.print(out);
                return;
            }
        }

        for (int i = 0; i < 2; i++) {
            int w = board2[i][0];
            int h = board2[i][1];
            if (w == X && h == Y) {
                out.append("Z\n");
                out.append(rect(0, 0, X, Y)).append('\n');
                System.out.print(out);
                return;
            }
        }

        for (int i = 0; i < 2; i++) {
            int w1 = board1[i][0];
            int h1 = board1[i][1];

            for (int j = 0; j < 2; j++) {
                int w2 = board2[j][0];
                int h2 = board2[j][1];

                if (w1 <= X && h1 <= Y && w2 <= X && h2 <= Y) {
                    if (h1 == Y && h2 == Y && w1 + w2 >= X) {
                        out.append(rect(0, 0, w1, Y)).append('\n');
                        out.append(rect(X - w2, 0, X, Y)).append('\n');
                        System.out.print(out);
                        return;
                    }

                    if (w1 == X && w2 == X && h1 + h2 >= Y) {
                        out.append(rect(0, 0, X, h1)).append('\n');
                        out.append(rect(0, Y - h2, X, Y)).append('\n');
                        System.out.print(out);
                        return;
                    }
                }
            }
        }

        System.out.println("EI SAA");
    }
}