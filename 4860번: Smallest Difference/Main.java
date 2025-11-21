import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) {
            return;
        }

        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int caseNum = 0; caseNum < T; caseNum++) {
            String digitsLine = br.readLine();
            while (digitsLine != null && digitsLine.trim().isEmpty()) {
                digitsLine = br.readLine();
            }
            if (digitsLine == null) {
                break;
            }

            String[] parts = digitsLine.trim().split("\\s+");
            int n = parts.length;
            int[] digits = new int[n];
            for (int i = 0; i < n; i++) {
                digits[i] = parts[i].charAt(0) - '0';
            }

            Arrays.sort(digits);
            long answer = solveCase(digits);
            sb.append(answer).append('\n');
        }
        System.out.print(sb.toString());
    }

    private static long solveCase(int[] digits) {
        int n = digits.length;
        int[] perm = digits.clone();
        long best = Long.MAX_VALUE;

        int[] splits;
        if (n % 2 == 0) {
            splits = new int[]{n / 2};
        } else {
            splits = new int[]{n / 2, n / 2 + 1};
        }

        boolean first = true;
        while (true) {
            for (int s = 0; s < splits.length; s++) {
                int k = splits[s];
                if (k <= 0 || k >= n) {
                    continue;
                }

                if (perm[0] == 0 && k > 1) {
                    continue;
                }
                if (perm[k] == 0 && (n - k) > 1) {
                    continue;
                }

                long a = 0;
                for (int i = 0; i < k; i++) {
                    a = a * 10 + perm[i];
                }
                long b = 0;
                for (int i = k; i < n; i++) {
                    b = b * 10 + perm[i];
                }

                long diff = (a >= b) ? (a - b) : (b - a);

                if (diff < best) {
                    best = diff;
                    if (best == 0) {
                        return 0L;
                    }
                }
            }

            if (!nextPermutation(perm)) {
                break;
            }
        }

        return best;
    }

    private static boolean nextPermutation(int[] a) {
        int n = a.length;
        int i = n - 2;
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }

        int j = n - 1;
        while (a[j] <= a[i]) {
            j--;
        }

        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;

        int left = i + 1;
        int right = n - 1;

        while (left < right) {
            tmp = a[left];
            a[left] = a[right];
            a[right] = tmp;
            left++;
            right--;
        }

        return true;
    }
}