import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        while (first != null && first.trim().isEmpty()) first = br.readLine();
        StringTokenizer st = new StringTokenizer(first);
        int N = Integer.parseInt(st.nextToken());
        long D = Long.parseLong(st.nextToken());

        BigDecimal[] frac = new BigDecimal[N];
        long sumFloors = 0L;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            while (s != null && s.trim().isEmpty()) s = br.readLine();
            BigDecimal x = new BigDecimal(s.trim());
            BigDecimal a = x.multiply(BigDecimal.valueOf(D));
            BigDecimal gBI = a.toBigInteger();
            long g = gBI.longValue();
            sumFloors += g;

            BigDecimal r = a.subtract(new BigDecimal(gBI));
            frac[i] = r;
        }

        int K = (int)(D - sumFloors);

        Arrays.sort(frac);
        BigDecimal sumTopK = BigDecimal.ZERO;
        for (int i = 0; i < K; i++) {
            sumTopK = sumTopK.add(frac[N - 1 - i]);
        }
        BigDecimal result = BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(K).subtract(sumTopK));

        if (result.signum() == 0) {
            System.out.println("0.0000000000");
        } else {
            System.out.println(result.setScale(10, RoundingMode.HALF_UP).toPlainString());
        }
    }
}