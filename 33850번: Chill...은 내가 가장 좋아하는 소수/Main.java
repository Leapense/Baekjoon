import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    private static final int MAX_SUM = 200_001;
    private static boolean[] isPrime = new boolean[MAX_SUM];

    private static class Grid {
        private final int[][] values;
        public Grid(int n) {
            this.values = new int[2][n];
        }

        public void setValue(int row, int col, int value) {
            this.values[row][col] = value;
        }

        public int getValue(int row, int col) {
            return this.values[row][col];
        }
    }

    private static void sieve() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p < MAX_SUM; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i < MAX_SUM; i += p) {
                    isPrime[i] = false;
                }
            }
        }
    }

    private static int getScore(int sum, int a, int b) {
        return isPrime[sum] ? a : b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Grid grid = new Grid(n);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            grid.setValue(0, i, Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            grid.setValue(1, i, Integer.parseInt(st.nextToken()));
        }

        sieve();

        long[] dp = new long[n + 1];
        if (n >= 1) {
            int verticalSum1 = grid.getValue(0, 0) + grid.getValue(1, 0);
            dp[1] = getScore(verticalSum1, a, b);
        }

        for (int i = 2; i <= n; i++) {
            int verticalSum = grid.getValue(0, i - 1) + grid.getValue(1, i - 1);
            long scoreFromVertical = dp[i - 1] + getScore(verticalSum, a, b);

            int horizontalSumTop = grid.getValue(0, i - 2) + grid.getValue(0, i - 1);
            int horizontalSumBottom = grid.getValue(1, i - 2) + grid.getValue(1, i - 1);
            long scoreFromHorizontal = dp[i - 2] + getScore(horizontalSumTop, a, b) + getScore(horizontalSumBottom, a, b);

            dp[i] = Math.max(scoreFromVertical, scoreFromHorizontal);
        }

        System.out.println(dp[n]);
    }
}