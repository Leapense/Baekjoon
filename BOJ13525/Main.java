import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();

            if (line == null) {
                System.err.println("Invalid input: no data provided.");
                return;
            }

            String s = line.trim();
            if (s.isEmpty()) {
                System.err.println("Invalid input: empty string.");
                return;
            }

            int[] cnt = new int[10];

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);

                if (!Character.isDigit(ch)) {
                    System.err.println("Invalid input: only digits 0-9 are allowed.");
                    return;
                }
                cnt[ch - '0']++;
            }

            int minNonZero = Integer.MAX_VALUE;
            int digit = -1;

            for (int d = 1; d <= 9; d++) {
                if (cnt[d] < minNonZero) {
                    minNonZero = cnt[d];
                    digit = d;
                }
            }

            StringBuilder answer = new StringBuilder();

            if (cnt[0] < minNonZero) {
                answer.append('1');
                for (int i = 0; i < cnt[0] + 1; i++) {
                    answer.append('0');
                }
            } else {
                for (int i = 0; i < minNonZero + 1; i++) {
                    answer.append((char)('0' + digit));
                }
            }

            System.out.print(answer);
        } catch (IOException e) {
            System.err.println("I/O error while reading input.");
        }
    }
}