import java.io.*;
import java.util.*;

public class Main {
    static int H, W;
    static char[][] grid;
    static boolean[][] visited;
    
    // 4-direction
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    
    // iterative stacks (store r * W + c)
    static int[] stackDie;
    static int[] stackDot;
    static int topDie, topDot;
    
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();
        
        while (true) {
            Integer hObj = fs.nextIntOrNull();
            if (hObj == null) break; // EOF
            H = hObj;
            W = fs.nextInt();
            
            if (H == 0 && W == 0) break;
            
            grid = new char[H][];
            for (int r = 0; r < H; r++) {
                String line = fs.next();
                grid[r] = line.toCharArray();
            }
            
            visited = new boolean[H][W];
            stackDie = new int[H * W];
            stackDot = new int[H * W];
            
            int[] freq = new int[7]; // dots per die: 1..6
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    if (grid[r][c] != '.' && !visited[r][c]) {
                        int dots = countDotsInDie(r, c);
                        freq[dots]++;
                    }
                }
            }
            
            out.append("Throw:");
            for (int v = 1; v <= 6; v++) {
                for (int k = 0; k < freq[v]; k++) {
                    out.append(' ').append(v);
                }
            }
            out.append('\n');
        }
        
        System.out.print(out.toString());
    }
    
    static int countDotsInDie(int sr, int sc) {
        int dots = 0;
        topDie = 0;
        stackDie[topDie++] = sr * W + sc;
        
        while (topDie > 0) {
            int idx = stackDie[--topDie];
            int r = idx / W;
            int c = idx % W;
            
            if (visited[r][c]) continue;
            visited[r][c] = true;
            
            if (grid[r][c] == 'X') {
                dots++;
                eraseDotComponent(r, c); // turn this X-blob into '*'
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (visited[nr][nc]) continue;
                if (grid[nr][nc] == '.') continue;
                stackDie[topDie++] = nr * W + nc;
            }
        }
        
        return dots;
    }
    
    static void eraseDotComponent(int sr, int sc) {
        topDot = 0;
        stackDot[topDot++] = sr * W + sc;
        
        while (topDot > 0) {
            int idx = stackDot[--topDot];
            int r = idx / W;
            int c = idx % W;
            
            if (grid[r][c] != 'X') continue;
            
            grid[r][c] = '*';
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (grid[nr][nc] == 'X') {
                    stackDot[topDot++] = nr * W + nc;
                }
            }
        }
    }
    
    static class FastScanner {
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
        
        private int skip() throws IOException {
            int c;
            while ((c = readByte()) != -1) {
                if (c > ' ') return c;
            }
            return -1;
        }
        
        Integer nextIntOrNull() throws IOException {
            int c = skip();
            if (c == -1) return null;
            
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }
            
            int val = 0;
            while (c > ' ' && c != -1) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            
            return val * sign;
        }
        
        int nextInt() throws IOException {
            Integer v = nextIntOrNull();
            return (v == null) ? 0 : v;
        }
        
        String next() throws IOException {
            int c = skip();
            if (c == -1) return null;
            StringBuilder sb = new StringBuilder();
            while (c > ' ' && c != -1) {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }
}