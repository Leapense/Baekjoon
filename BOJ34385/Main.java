import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double k = Double.parseDouble(br.readLine().trim());
        int n = Integer.parseInt(br.readLine().trim());
        int r = Integer.parseInt(br.readLine().trim());

        double oneAttemptSuccess = Math.pow(1.0 - k, n);
        double answer = 1.0 - Math.pow(1.0 - oneAttemptSuccess, r);

        System.out.println(answer);
    }
}
