import java.io.*;
import java.util.*;

public class Main {
    private static int toMinutes(String hhmm) {
        int hh = (hhmm.charAt(0) - '0') * 10 + (hhmm.charAt(1) - '0');
        int mm = (hhmm.charAt(3) - '0') * 10 + (hhmm.charAt(4) - '0');
        return hh * 60 + mm;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = fs.nextInt();
        int[] students = new int[N];
        int studentIdx = 0;
        int teacherTime = -1;

        for (int i = 0; i < N + 1; i++) {
            String timeStr = fs.next();
            String role = fs.next();
            int time = toMinutes(timeStr);

            if ("teacher".equals(role)) {
                teacherTime = time;
            } else {
                students[studentIdx++] = time;
            }
        }

        int schoolTime = toMinutes(fs.next());

        int count = 0;
        for (int i = 0; i < N; i++) {
            int arr = students[i];
            if (arr >= schoolTime && arr >= teacherTime) {
                count++;
            }
        }

        System.out.print(count);
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
                if (len <= 0)
                    return -1;
            }

            return buffer[ptr++];
        }

        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = readByte()) != -1 && c <= ' ') {

            }
            if (c == -1)
                return null;
            do {
                sb.append((char) c);
                c = readByte();
            } while (c != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() throws IOException {
            int c;
            while ((c = readByte()) != -1 && c <= ' ') {

            }
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
}