import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long cur = Long.parseLong(br.readLine().trim());
        int E = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < E; i++) {
            String op = br.readLine().trim();
            long q = Long.parseLong(br.readLine().trim());
            if (op.equals("+")) {
                cur += q;
            } else {
                cur -= q;
            }
        }
        
        System.out.println(cur);
    }
}