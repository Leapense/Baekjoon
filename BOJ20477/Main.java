import java.io.*;
import java.util.*;

public class Main {
    static class Person {
        int time;
        int amount;
        int index;

        Person(int time, int amount, int index) {
            this.time = time;
            this.amount = amount;
            this.index = index;
        }
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int m = fs.nextInt();
        int v = fs.nextInt();
        int power = fs.nextInt();
        int k = fs.nextInt();

        Person[] people = new Person[m];
        for (int i = 0; i < m; i++) {
            int t = fs.nextInt();
            int a = fs.nextInt();
            people[i] = new Person(t, a, i);
        }

        Arrays.sort(people, Comparator.comparingInt(p -> p.time));

        double[] ans = new double[m];
        double finishTime = 0.0;
        double water = 0.0;

        for (Person p : people) {
            double start = Math.max((double) p.time, finishTime);

            double temp;
            if (water <= 0.0) {
                temp = 20.0;
            } else {
                double cooled = 100.0 - k * (start - finishTime);
                temp = Math.max(20.0, cooled);
            }

            if (water + 1e-12 < p.amount) {
                temp = (water * temp + 20.0 * (v - water)) / v;
                water = v;
            }

            double heatTime = water * (100.0 - temp) / power;
            double drinkTime = start + heatTime;
            ans[p.index] = drinkTime;

            water -= p.amount;
            finishTime = drinkTime;

            if (water < 1e-12) {
                water = 0.0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(ans[i]).append('\n');
        }

        System.out.print(sb.toString());
    }
}