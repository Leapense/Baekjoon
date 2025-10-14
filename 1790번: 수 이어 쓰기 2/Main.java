import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        long N = Long.parseLong(st.nextToken());
        long k;

        if (st.hasMoreTokens()) {
            k = Long.parseLong(st.nextToken());
        } else {
            st = new StringTokenizer(br.readLine());
            k = Long.parseLong(st.nextToken());
        }

        long total = 0;
        for (long start = 1, d = 1; start <= N; start *= 10, d++) {
            long end = Math.min(N, start * 10 - 1);
            long count = end - start + 1;
            total += count * d;
        }

        if (k > total) {
            System.out.println(-1);
            return;
        }

        long start = 1;
        int d = 1;

        while (true) {
            long end = Math.min(N, start * 10 - 1);
            long count = end - start + 1;
            long blockDigits = count * d;

            if (k > blockDigits) {
                k -= blockDigits;
                start *= 10;
                d++;
            } else {
                long offset = k - 1;
                long number = start + offset / d;
                int digitIndex = (int) (offset % d);
                String s = Long.toString(number);
                System.out.println(s.charAt(digitIndex));
                return;
            }
        }
    }
}