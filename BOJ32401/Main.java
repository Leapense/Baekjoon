import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        String S = br.readLine().trim();

        List<Integer> aPos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == 'A')
                aPos.add(i);
        }

        int ans = 0;
        for (int k = 0; k + 1 < aPos.size(); k++) {
            int p = aPos.get(k);
            int q = aPos.get(k + 1);

            if (q - p < 2)
                continue;

            int nCnt = 0;
            for (int i = p + 1; i <= q - 1; i++) {
                if (S.charAt(i) == 'N')
                    nCnt++;
            }

            if (nCnt == 1)
                ans++;
        }

        System.out.println(ans);
    }
}