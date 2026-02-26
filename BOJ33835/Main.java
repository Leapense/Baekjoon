import java.io.BufferedInputStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final int MAX_N = 1_000_000;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in))) {
            if (!sc.hasNextLong()) {
                System.err.println("Error: Input is empty or invalid.");
                return;
            }

            long rawN = sc.nextLong();

            if (rawN < 2 || rawN > MAX_N) {
                System.err.printf("Error: N must be between 2 and %d.%n", MAX_N);
                return;
            }

            int N = (int) rawN;
            if (!sc.hasNextLong()) {
                System.err.println("Error: Insufficient data for coordinates.");
                return;
            }

            long x1 = sc.nextLong();
            long y1 = sc.nextLong();

            long xN = x1;
            long yN = y1;

            for (int i = 2; i <= N; i++) {
                if (sc.hasNextLong()) {
                    xN = sc.nextLong();
                    yN = sc.nextLong();
                } else {
                    System.err.println("Error: Unexpected end of input.");
                    return;
                }
            }

            long dxL = x1 - xN;
            long dyL = y1 - yN;

            double dx = (double) dxL;
            double dy = (double) dyL;

            double ans = Math.hypot(dx, dy);

            System.out.printf("%.10f%n", ans);
        } catch (InputMismatchException e) {
            System.err.println("Error: Input format invalid. Expected integers.");
        } catch (Exception e) {
            System.err.println("Error: An unexpected error occurred processing input.");
        }
    }
}