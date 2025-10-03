import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(nextNonEmptyLine(br));
        int Wr = Integer.parseInt(st.nextToken());
        int Hr = Integer.parseInt(st.nextToken());
        char[][] ref = readGrid(br, Hr, Wr);

        st = new StringTokenizer(nextNonEmptyLine(br));
        int Ws = Integer.parseInt(st.nextToken());
        int Hs = Integer.parseInt(st.nextToken());
        char[][] sky = readGrid(br, Hs, Ws);

        int sxFound = -1, syFound = -1;
        outer:
        for (int sy = 0; sy <= Hs - Hr; sy++) {
            for (int sx = 0; sx <= Ws - Wr; sx++) {
                if (matchAt(sky, ref, sx, sy)) {
                    sxFound = sx;
                    syFound = sy;
                    break outer;
                }
            }
        }

        int dx = sxFound - (Ws - Wr) / 2;
        int dy = syFound - (Hs - Hr) / 2;

        System.out.println(dx + " " + dy);
    }

    private static boolean matchAt(char[][] sky, char[][] ref, int sx, int sy) {
        int Hr = ref.length, Wr = ref[0].length;
        for (int y = 0; y < Hr; y++) {
            for (int x = 0; x < Wr; x++) {
                if (sky[sy + y][sx + x] != ref[y][x]) return false;
            }
        }

        return true;
    }

    private static char[][] readGrid(BufferedReader br, int h, int w) throws IOException {
        char[][] g = new char[h][w];
        int row = 0;
        while (row < h) {
            String line = br.readLine();
            if (line == null) throw new IOException("Unexpected end of input while reading grid.");
            if (line.isEmpty()) continue;
            for (int i = 0; i < w; i++) g[row][i] = line.charAt(i);
            row++;
        }
        return g;
    }

    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        if (s == null) throw new EOFException("Unexpected end of input while reading header line.");
        return s;
    }
}