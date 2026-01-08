import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int N = Integer.parseInt(br.readLine().trim());
        String S = br.readLine().trim();
        String T = br.readLine().trim();

        int aoiWin = 0;
        int bitaroWin = 0;

        for (int i = 0; i < N; i++) {
            char a = S.charAt(i);
            char b = T.charAt(i);

            if (a == 'S' && b == 'P') {
                aoiWin++;
            } else if ((a == 'R' && b == 'P') || (a == 'S' && b == 'R')) {
                bitaroWin++;
            }
        }
        System.out.println(aoiWin + " " + bitaroWin);
    }
}
