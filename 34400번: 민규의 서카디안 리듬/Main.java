import java.io.*;
import java.util.*;

public class Main {
    private static final class FastScanner {
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
            } while (c <= ' ' && c != -1);
            
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
        
        int T = fs.nextInt();
        final int CYCLE = 50;
        final int AWAKE = 34;
        
        for (int i = 0; i < T; i++) {
            int t = fs.nextInt();
            int units = 2 * t + 1;
            int pos = units % CYCLE;
            
            if (pos < AWAKE) sb.append("ONLINE\n");
            else sb.append("OFFLINE\n");
        }
        
        System.out.print(sb.toString());
    }
}