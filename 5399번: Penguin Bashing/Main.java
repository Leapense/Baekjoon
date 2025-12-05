import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final double G = 9.81;
    static final double V0 = 25.0;
    static final double START_HEIGHT = 1.0;
    static final double FRICTION_DECEL = 5.0;

    static class Obstacle implements Comparable<Obstacle> {
        double x;
        String type;

        public Obstacle(double x, String type) {
            this.x = x;
            this.type = type;
        }

        @Override
        public int compareTo(Obstacle other) {
            return Double.compare(this.x, other.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            List<Obstacle> obstacles = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                String type = st.nextToken();
                obstacles.add(new Obstacle(x, type));
            }

            Collections.sort(obstacles);
            solve(obstacles);
        }
    }

    private static void solve(List<Obstacle> obstacles) {
        long maxScore = Long.MIN_VALUE;
        int bestAngle = -90;

        for (int angle = -90; angle <= 90; angle++) {
            long score = simulate(angle, obstacles);

            if (score > maxScore) {
                maxScore = score;
                bestAngle = angle;
            }
        }

        System.out.println(maxScore + " " + bestAngle);
    }

    private static long simulate(int angleDeg, List<Obstacle> obstacles) {
        double theta = Math.toRadians(angleDeg);
        double vx = V0 * Math.cos(theta);
        double vy = V0 * Math.sin(theta);

        double timeToLand = (vy + Math.sqrt(vy * vy + 2 * G * START_HEIGHT)) / G;
        double currentX = vx * timeToLand;
        double currentV = vx;

        int obsIdx = 0;
        while (obsIdx < obstacles.size() && obstacles.get(obsIdx).x <= currentX) {
            obsIdx++;
        }

        boolean spikeHit = false;
        while (currentV > 1e-9) {
            double slideDist = currentV / FRICTION_DECEL;
            double stopX = currentX + slideDist;

            Obstacle nextObs = null;
            if (obsIdx < obstacles.size()) {
                Obstacle candidate = obstacles.get(obsIdx);
                if (candidate.x <= stopX) {
                    nextObs = candidate;
                }
            }

            if (nextObs == null) {
                currentX = stopX;
                currentV = 0;
            } else {
                obsIdx++;
                if (nextObs.type.equals("spike")) {
                    spikeHit = true;
                    break;
                } else if (nextObs.type.equals("mine")) {
                    double distToMine = nextObs.x - currentX;
                    double vAtImpact = currentV - (distToMine * FRICTION_DECEL);
                    currentX = nextObs.x + 2.0;
                    currentV = vAtImpact + 4.0;

                    while (obsIdx < obstacles.size() && obstacles.get(obsIdx).x <= currentX) {
                        obsIdx++;
                    }
                }
            }
        }

        if (spikeHit) {
            return -100;
        } else {
            return Math.round(currentX * 100);
        }
    }
}