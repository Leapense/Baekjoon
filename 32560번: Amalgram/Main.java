import java.io.*;
import java.util.*;

public class Main {
    private static final class FastInput {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastInput(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++] & 0xff;
        }

        boolean readLineCounts(int[] counts) throws IOException {
            int c;
            do {
                c = read();
                if (c == -1) return false;
            } while (c == '\n' || c == '\r');

            while (c != -1 && c != '\n' && c != '\r') {
                counts[c - 'a']++;
                c = read();
            }

            return true;
        }
    }

    private static void appendRepeat(StringBuilder sb, char ch, int times) {
        final int CHUNK = 1 << 14;
        char[] buf = new char[Math.min(CHUNK, Math.max(1, times))];
        Arrays.fill(buf, ch);
        
        while (times > 0) {
            int take = Math.min(times, buf.length);
            sb.append(buf, 0, take);
            times -= take;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput fi = new FastInput(System.in);

        int[] cntA = new int[26];
        int[] cntB = new int[26];
        
        fi.readLineCounts(cntA);
        fi.readLineCounts(cntB);

        int total = 0;
        int[] need = new int[26];
        for (int i = 0; i < 26; i++) {
            need[i] = Math.max(cntA[i], cntB[i]);
            total += need[i];
        }

        StringBuilder sb = new StringBuilder(total);
        for (int i = 0; i < 26; i++) {
            int times = need[i];
            if (times > 0) appendRepeat(sb, (char)('a' + i), times);
        }

        System.out.print(sb.toString());
    }
}