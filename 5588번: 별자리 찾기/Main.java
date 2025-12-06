import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m = Integer.parseInt(br.readLine());
        ArrayList<Point> constellation = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            constellation.add(new Point(x, y));
        }

        int n = Integer.parseInt(br.readLine());
        ArrayList<Point> skyList = new ArrayList<>();
        HashSet<Point> skySet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            skyList.add(new Point(x, y));
            skySet.add(new Point(x, y));
        }

        Point baseStar = constellation.get(0);

        for (Point skyStar : skyList) {
            int moveX = skyStar.x - baseStar.x;
            int moveY = skyStar.y - baseStar.y;

            boolean isMatch = true;
            for (int i = 1; i < m; i++) {
                Point target = constellation.get(i);
                Point movedTarget = new Point(target.x + moveX, target.y + moveY);

                if (!skySet.contains(movedTarget)) {
                    isMatch = false;
                    break;
                }
            }

            if (isMatch) {
                System.out.println(moveX + " " + moveY);
                return;
            }
        }
    }
}