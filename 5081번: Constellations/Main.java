import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static class Star {
        int x, y;
        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class DSU {
        private int[] parent;
        public DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootA = find(i);
            int rootB = find(j);
            if (rootA != rootB) {
                parent[rootA] = rootB;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int skyCounter = 1;
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt()) break;
            int n = scanner.nextInt();

            if (n == 0) break;
            List<Star> stars = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                stars.add(new Star(scanner.nextInt(), scanner.nextInt()));
            }

            int constellations = solveSky(n, stars);

            System.out.printf("Sky %d contains %d constellations.%n", skyCounter, constellations);
            skyCounter++;
        }

        scanner.close();
    }

    private static int solveSky(int n, List<Star> stars) {
        DSU dsu = new DSU(n);
        if (n <= 1) return n;

        for (int i = 0; i < n; i++) {
            long minDistanceSq = Long.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long dist = getDistanceSq(stars.get(i), stars.get(j));
                if (dist < minDistanceSq) {
                    minDistanceSq = dist;
                }
            }

            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long dist = getDistanceSq(stars.get(i), stars.get(j));
                if (dist == minDistanceSq) {
                    dsu.union(i, j);
                }
            }
        }

        Set<Integer> uniqueRoots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            uniqueRoots.add(dsu.find(i));
        }

        return uniqueRoots.size();
    }

    private static long getDistanceSq(Star a, Star b) {
        long dx = a.x - b.x;
        long dy = a.y - b.y;
        return dx * dx + dy * dy;
    }
}