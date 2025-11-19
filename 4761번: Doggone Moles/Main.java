import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) {
            this.in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) {
                    return -1;
                }
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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int setNo = 1;
        while (true) {
            int W = fs.nextInt();
            int L = fs.nextInt();
            int N = fs.nextInt();
            int T = fs.nextInt();

            if (W == -1) {
                break;
            }

            if (W == 0 && L == 0 && N == 0 && T == 0) {
                break;
            }

            boolean[][][] allowed = new boolean[T][L + 1][W + 1];

            for (int t = 0; t < T; t++) {
                for (int y = 0; y <= L; y++) {
                    Arrays.fill(allowed[t][y], true);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int t = 0; t < T; t++) {
                    int x = fs.nextInt();
                    int y = fs.nextInt();

                    if (x < 0 || x > W || y < 0 || y > L) {
                        continue;
                    }

                    allowed[t][y][x] = false;
                    if (x > 0) {
                        allowed[t][y][x - 1] = false;
                    }
                    if (x < W) {
                        allowed[t][y][x + 1] = false;
                    }
                    if (y > 0) {
                        allowed[t][y - 1][x] = false;
                    }
                    if (y < L) {
                        allowed[t][y + 1][x] = false;
                    }
                }
            }

            boolean[][] cur = new boolean[L + 1][W + 1];

            for (int y = 0; y <= L; y++) {
                System.arraycopy(allowed[0][y], 0, cur[y], 0, W + 1);
            }

            for (int t = 0; t < T - 1; t++) {
                boolean[][] next = new boolean[L + 1][W + 1];

                for (int y = 0; y <= L; y++) {
                    for (int x = 0; x <= W; x++) {
                        if (!cur[y][x]) {
                            continue;
                        }

                        if (!allowed[t + 1][y][x]) {
                            continue;
                        }

                        if (allowed[t + 1][y][x]) {
                            next[y][x] = true;
                        }
                        if (x > 0 && allowed[t + 1][y][x - 1]) {
                            next[y][x - 1] = true;
                        }
                        if (x < W && allowed[t + 1][y][x + 1]) {
                            next[y][x + 1] = true;
                        }
                        if (y > 0 && allowed[t + 1][y - 1][x]) {
                            next[y - 1][x] = true;
                        }
                        if (y < L && allowed[t + 1][y + 1][x]) {
                            next[y + 1][x] = true;
                        }
                    }
                }

                cur = next;
            }

            out.printf("Observation Set %d%n", setNo++);
            boolean any = false;
            int countOnLine = 0;

            for (int y = 0; y <= L; y++) {
                for (int x = 0; x <= W; x++) {
                    if (!cur[y][x]) {
                        continue;
                    }
                    if (!any) {
                        any = true;
                    }

                    if (countOnLine > 0) {
                        out.print(' ');
                    }

                    out.printf("(%d,%d)", x, y);
                    countOnLine++;

                    if (countOnLine == 8) {
                        out.println();
                        countOnLine = 0;
                    }
                }
            }

            if (!any) {
                out.println("No possible locations");
            } else if (countOnLine != 0) {
                out.println();
            }
        }

        out.flush();
    }
}