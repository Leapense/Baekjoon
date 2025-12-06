import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MAX_INDEX = 95;
    private static long[] fibValues = new long[MAX_INDEX];
    public static void main(String[] args) {
        precomputeFibonacci();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            if (sc.hasNextLong()) {
                long target = sc.nextLong();
                solve(target);
            } else {
                sc.next();
            }
        }
        sc.close();
    }

    private static void precomputeFibonacci() {
        fibValues[1] = 1;
        fibValues[2] = 2;
        for (int i = 3; i < MAX_INDEX; i++) {
            fibValues[i] = fibValues[i - 1] + fibValues[i - 2];
            if (fibValues[i] < 0) {
                fibValues[i] = Long.MAX_VALUE;
            }
        }
    }

    private static void solve(long target) {
        long currentAmount = target;
        List<Integer> sizes = new ArrayList<>();
        List<Long> values = new ArrayList<>();

        for (int i = MAX_INDEX - 1; i >= 1; i--) {
            if (fibValues[i] <= 0 || fibValues[i] > currentAmount) {
                continue;
            }

            currentAmount -= fibValues[i];
            sizes.add(i);
            values.add(fibValues[i]);

            if (currentAmount == 0) break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(target).append("\n");

        for (int i = 0; i < sizes.size(); i++) {
            sb.append(sizes.get(i));
            if (i < sizes.size() - 1) sb.append(" ");
        }

        sb.append("\n");

        for (int i = 0; i < values.size(); i++) {
            sb.append(values.get(i));
            if (i < values.size() - 1) sb.append(" ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }
}