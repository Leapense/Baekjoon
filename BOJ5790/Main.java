import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) break;

            int totalMinutes = 0;
            int nightMinutes = 0;
            boolean violation = false;

            for (int i = 0; i < n; i++) {
                int sunriseMin = toMinutes(sc.next());
                int sunsetMin  = toMinutes(sc.next());
                int startMin   = toMinutes(sc.next());
                int finishMin  = toMinutes(sc.next());

                int duration = finishMin - startMin;

                if (duration > 120) {
                    violation = true;
                }

                totalMinutes += duration;

                int dayPortion = Math.max(0, Math.min(finishMin, sunsetMin + 1) - Math.max(startMin, sunriseMin));
                int nightPortion = duration - dayPortion;

                if (nightPortion * 2 >= duration) {
                    nightMinutes += duration;
                }
            }

            if (!violation && totalMinutes >= 3000 && nightMinutes >= 600) {
                System.out.println("PASS");
            } else {
                System.out.println("NON");
            }
        }
    }

    static int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}