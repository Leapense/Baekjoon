import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        int r = t % 3600;
        int delta = (r == 0) ? 3600 : (3600 - r);
        int x = (delta + 59) / 60;
        System.out.println(x);
    }
}