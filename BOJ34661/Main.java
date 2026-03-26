import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int N = fs.nextInt();
            int M = fs.nextInt();

            int emptyCount = 0;

            for (int i = 0; i < N; i++) {
                String row = fs.next();
                for (int j = 0; j < M; j++) {
                    if (row.charAt(j) == '.') {
                        emptyCount++;
                    }
                }
            }

            if ((emptyCount & 1) == 1) {
                sb.append("sewon\n");
            } else {
                sb.append("pizza\n");
            }
        }

        System.out.print(sb.toString());
    }

    static class FastScanner {
        private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}