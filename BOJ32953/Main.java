import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    private static final int BUFFER_SIZE = 1 << 16;
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        solve(in, out);

        out.flush();
        out.close();
    }

    private static void solve(InputReader in, PrintWriter out) {
        try {
            int numberOfSets = in.nextInt();
            int threshold = in.nextInt();
            Map<Integer, Integer> frequencyMap = new HashMap<>(BUFFER_SIZE);

            for (int i = 0; i < numberOfSets; i++) {
                int setSize = in.nextInt();
                for (int j = 0; j < setSize; j++) {
                    int id = in.nextInt();
                    frequencyMap.merge(id, 1, Integer::sum);
                }
            }

            long answer = frequencyMap.values().stream().filter(count -> count >= threshold).count();
            out.print(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buffer = new byte[BUFFER_SIZE];
        private int ptr = 0;
        private int len = 0;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                ptr = 0;
                len = stream.read(buffer);
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        public int nextInt() throws IOException {
            int c = readByte();
            while (c <= ' ') {
                if (c == -1) throw new EOFException();
                c = readByte();
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int res = 0;
            while (c > ' ') {
                if (c < '0' || c > '9') {
                    throw new NumberFormatException("Invalid character found: " + (char)c);
                }
                res = res * 10 + (c - '0');
                c = readByte();
            }

            return res * sign;
        }
    }
}