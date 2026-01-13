import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        String S = br.readLine().trim();
        
        int countO = 0;
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == 'O') countO++;
        }
        
        int need = (N + 1) / 2;
        System.out.println(countO >= need ? "Yes" : "No");
    }
}