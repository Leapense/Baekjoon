import java.io.*;
import java.util.*;

public class Main {
    static class FastReader {
        private final int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return neg ? -ret : ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int N = fr.nextInt();
        int M = fr.nextInt();

        int[] A = new int[N];
        for (int i = 0; i < N; i++) A[i] = fr.nextInt();

        Arrays.sort(A);

        long count = 0L;
        int maxSum = -1;

        int r = 0;
        for (int i = 0; i < N; i++) {
            if (r < i) r = i;
            while (r + 1 < N && A[r + 1] - A[i] <= M) {
                r++;
            }

            int t = r - i;
            if (t >= 2) {
                count += (long) t * (t - 1) / 2;
                int sum = A[i] + A[r] + A[r - 1];
                if (sum > maxSum) maxSum = sum;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (count == 0) {
            sb.append("-1\n");
        } else {
            sb.append(count).append(' ').append(maxSum).append('\n');
        }

        out.print(sb);
        out.close();
    }
}