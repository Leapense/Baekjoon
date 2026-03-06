import java.io.*;
import java.util.*;

public class Main {
    static final int[] PRICE = {319, 419, 450, 519, 599, 600, 630, 719};
    static final int[] DH = {1024, 1024, 960, 2048, 1136, 1280, 1080, 1136};
    static final int[] DW = {768, 600, 640, 1536, 640, 800, 1920, 640};

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        while (true) {
            long H = fs.nextLong();
            long W = fs.nextLong();
            if (H == 0 && W == 0) break;

            int bestPrice = Integer.MAX_VALUE;
            long bestArea = -1;
            long bestScreenArea = 1;

            for (int i = 0; i < PRICE.length; i++) {
                long screenArea = 1L * DH[i] * DW[i];
                long areaNormal = displayedArea(H, W, DH[i], DW[i]);
                long areaRotated = displayedArea(H, W, DW[i], DH[i]);
                long area = Math.max(areaNormal, areaRotated);

                long left = area * bestScreenArea;
                long right = bestArea * screenArea;
                if (bestArea == -1 || left > right || (left == right && PRICE[i] < bestPrice)) {
                    bestArea = area;
                    bestScreenArea = screenArea;
                    bestPrice = PRICE[i];
                }
            }

            out.append(bestPrice).append('\n');
        }

        System.out.print(out);
    }

    static long displayedArea(long movieH, long movieW, long deviceH, long deviceW) {
        if (deviceH * movieW <= deviceW * movieH) {
            long shownW = (deviceH * movieW) / movieH;
            return deviceH * shownW;
        } else {
            long shownH = (deviceW * movieH) / movieW;
            return shownH * deviceW;
        }
    }

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

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= '-');

            long sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }
}