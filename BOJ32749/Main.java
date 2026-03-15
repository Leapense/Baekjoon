import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        String X = br.readLine().trim();
        char[] digits = X.toCharArray();

        int blockSize = 1 << (N - T);
        int count = 1 << T;

        int bestStart = 0;
        for (int i = 1; i < count; i++) {
            int currentStart = i * blockSize;
            if (compareBlock(digits, currentStart, bestStart, blockSize) > 0) {
                bestStart = currentStart;
            }
        }

        System.out.print(new String(digits, bestStart, blockSize));
    }

    private static int compareBlock(char[] digits, int startA, int startB, int len) {
        for (int i = 0; i < len; i++) {
            if (digits[startA + i] != digits[startB + i]) {
                return digits[startA + i] - digits[startB + i];
            }
        }

        return 0;
    }
}