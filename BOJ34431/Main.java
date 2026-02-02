import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long W = Long.parseLong(br.readLine().trim());
        long m = Long.parseLong(br.readLine().trim());
        long C = Long.parseLong(br.readLine().trim());

        long denom = 6_000_000L;
        long numer = W * m * C;

        long dollars = (numer + denom - 1) / denom;
        System.out.println(dollars);
    }
}