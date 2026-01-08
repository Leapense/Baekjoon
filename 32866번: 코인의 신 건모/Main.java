import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        int N = Integer.parseInt(br.readLine().trim());
        int X = Integer.parseInt(br.readLine().trim());

        double ans = (100.0 * X) / (100.0 - X);
        System.out.printf("%.10f%n", ans);
    }
}
