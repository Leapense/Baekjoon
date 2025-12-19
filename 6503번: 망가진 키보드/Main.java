import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            line = line.trim();
            if (line.isEmpty())
                continue;

            int m = Integer.parseInt(line);
            if (m == 0)
                break;

            String s = br.readLine();
            if (s == null)
                s = "";

            int ans = longestAtMostDistinct(s, m);
            out.append(ans).append('\n');
        }

        System.out.print(out.toString());
    }

    private static int longestAtMostDistinct(String s, int m) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        int[] freq = new int[65536];
        int l = 0;
        int distinct = 0;
        int best = 0;

        for (int r = 0; r < n; r++) {
            char c = arr[r];
            if (freq[c] == 0)
                distinct++;
            freq[c]++;

            while (distinct > m) {
                char leftChar = arr[l++];
                freq[leftChar]--;
                if (freq[leftChar] == 0)
                    distinct--;
            }

            int len = r - l + 1;
            if (len > best)
                best = len;
        }

        return best;
    }
}