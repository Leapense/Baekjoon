import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long d = Long.parseLong(br.readLine().trim());
        double ans = d * (Math.PI / 2.0 - 1.0);

        System.out.printf("%.15f%n", ans);
    }
}