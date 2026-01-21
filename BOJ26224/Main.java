import java.io.*;
import java.util.*;

public class Main {
    private static final long SCALE = 100_000L;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        String name0 = fs.next();
        if (name0 == null) return;
        long inc0 = Long.parseLong(fs.next());
        String name1 = fs.next();
        String mulStr = fs.next();
        long inc1 = Long.parseLong(fs.next());

        long mulScaled = parseMulScaled(mulStr);

        long left = inc0 * mulScaled;
        long right = inc1 * SCALE;

        if (left > right) {
            System.out.println("Power up, Evolve");
        } else if (left < right) {
            System.out.println("Evolve, Power up");
        } else {
            System.out.println("Whatever");
        }
    }

    private static long parseMulScaled(String s) {
        int dot = s.indexOf('.');
        if (dot < 0) {
            return Long.parseLong(s) * SCALE;
        }
        String intPart = s.substring(0, dot);
        String fracPart = s.substring(dot + 1);

        if (fracPart.length() > 5) {
            fracPart = fracPart.substring(0, 5);
        } else {
            while (fracPart.length() < 5) fracPart += "0";
        }

        long ip = Long.parseLong(intPart);
        long fp = Long.parseLong(fracPart);

        return ip * SCALE + fp;
    }

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
    }
}