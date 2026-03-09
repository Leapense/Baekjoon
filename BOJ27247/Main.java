import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static long encode(int x, int y) {
        return (((long) x) << 32) ^ (y & 0xffffffffL);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        long d = fs.nextInt();
        int k = fs.nextInt();

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int x = 0;
        int y = 0;
        int dir = 0;

        int moved = 0;
        Set<Long> visited = new HashSet<>();
        visited.add(encode(x, y));

        int minX = 0, maxX = 0, minY = 0, maxY = 0;

        while (moved < n) {
            int steps = (int) Math.min(d, n - moved);
            for (int i = 0; i < steps; i++) {
                x += dx[dir];
                y += dy[dir];
                visited.add(encode(x, y));

                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);

                moved++;
            }

            if (moved == n) {
                break;
            }

            dir = (dir + 1) % 4;
            steps = (int) Math.min(d, n - moved);

            for (int i = 0; i < steps; i++) {
                x += dx[dir];
                y += dy[dir];
                visited.add(encode(x, y));
                
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);

                moved++;
            }

            if (moved == n) {
                break;
            }

            dir = (dir + 1) % 4;
            d *= k;
        }

        int height = maxY - minY + 1;
        int width = maxX - minX + 1;

        StringBuilder sb = new StringBuilder();
        sb.append(height).append(' ').append(width).append('\n');

        for (int rowY = maxY; rowY >= minY; rowY--) {
            for (int colX = minX; colX <= maxX; colX++) {
                if (visited.contains(encode(colX, rowY))) {
                    sb.append('*');
                } else {
                    sb.append('.');
                }
            }

            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }

            return Integer.parseInt(st.nextToken());
        }
    }
}