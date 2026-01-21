import java.io.*;

public class Main {
    private static int baseDivision(int r) {
        if (r <= 1600) return 3;
        if (r <= 1900) return 2;
        return 1;
    }

    private static int maxBaseRating(int division) {
        if (division == 3) return 1600;
        if (division == 2) return 1900;
        return 5000;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int r = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();

        boolean[] avail = new boolean[4];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            avail[c - '0'] = true;
        }

        int base = baseDivision(r);
        StringBuilder out = new StringBuilder();

        if (avail[base]) {
            out.append(base).append('\n');
        } else {
            for (int d = 1; d <= 3; d++) {
                if (!avail[d]) continue;
                out.append(d);
                if (r > maxBaseRating(d)) out.append('*');
                out.append('\n');
            }
        }

        System.out.print(out.toString());
    }
}