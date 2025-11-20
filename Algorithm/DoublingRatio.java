import java.util.Random;

public class DoublingRatio {
    @FunctionalInterface
    public interface IntArrayAlgorithm {
        int run(int[] a);
    }

    private static double timeTrial(IntArrayAlgorithm alg, int N, Random rnd) {
        final int MAX = 1_000_000;
        int[] a = new int[N];

        // 1) generate input (this part is not timed)
        for (int i = 0; i < N; i++) {
            // uniform random integers in [-MAX, MAX]
            a[i] = rnd.nextInt(2 * MAX + 1) - MAX;
        }

        // 2) time the algorithm
        long start = System.nanoTime();
        int result = alg.run(a);
        long elapsed = System.nanoTime() - start;

        // (optional) use result to avoid any unrealistic optimization.
        if (result == Integer.MIN_VALUE) {
            System.out.println("Impossible branch, just to use result.");
        }

        return elapsed / 1_000_000_000.0; // seconds
    }

    private static void runExperiment(String name,
                                      IntArrayAlgorithm alg,
                                      int N0,
                                      Random rnd) {
        System.out.println("=== " + name + " Doubling Ratio Experiment ===");

        double prev = timeTrial(alg, N0, rnd);
        System.out.printf("%10s %12s %10s%n", "N", "time (s)", "ratio");
        System.out.printf("%10d %12.4f %10s%n", N0, prev, "-");

        for (int N = N0 * 2; ; N += N) {
            double time = timeTrial(alg, N, rnd);
            double ratio = time / prev;
            System.out.printf("%10d %12.4f %10.2f%n", N, time, ratio);
            prev = time;

            // 종료 조건은 필요에 따라 조정 (예: 시간 너무 길어지면 break)
            if (time > 60.0) { // 한 번에 너무 오래 걸리지 않도록
                break;
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        runExperiment("TwoSum (brute force)", TwoSum::count, 1_000, rnd);
        runExperiment("ThreeSum (brute force)", ThreeSum::count, 200, rnd);
        runExperiment("ThreeSumFast (sort + binary search)", ThreeSumFast::count, 1_000, rnd);
    }
}