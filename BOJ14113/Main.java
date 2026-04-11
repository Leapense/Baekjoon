import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        long D = Long.parseLong(st.nextToken());
        long E = Long.parseLong(st.nextToken());

        long bags = 0;
        bags += A;

        bags += B;
        long use1With4 = Math.min(B, E);
        E -= use1With4;

        long pair32 = Math.min(C, D);
        bags += pair32;
        C -= pair32;
        D -= pair32;

        bags += C;
        long use1With3 = Math.min(E, 2 * C);
        E -= use1With3;

        long pair22 = D / 2;
        bags += pair22;

        long use1With22 = Math.min(E, pair22);
        E -= use1With22;

        if (D % 2 == 1) {
            bags += 1;
            long use1With2 = Math.min(E, 3);
            E -= use1With2;
        }

        bags += (E + 4) / 5;
        System.out.print(bags);
        br.close();
    }
}