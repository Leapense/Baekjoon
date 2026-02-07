import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        long d = sc.nextLong();
        long k = sc.nextLong();

        long cur = a;
        Map<Long, Long> seen = new HashMap<>();
        List<Long> history = new ArrayList<>();

        for (long day = 1; day <= k; day++) {
            if (seen.containsKey(cur)) {
                long cycleStart = seen.get(cur);
                long cycleLen = day - cycleStart;
                long offset = (k - cycleStart) % cycleLen;
                System.out.println(history.get((int)(cycleStart - 1 + offset)));
                return;
            }

            seen.put(cur, day);

            long afterIncubator = cur * b;
            if (afterIncubator < c) {
                System.out.println(0);
                return;
            }

            long afterExperiments = afterIncubator - c;
            cur = Math.min(afterExperiments, d);

            history.add(cur);
            if (cur == 0) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(cur);
    }
}