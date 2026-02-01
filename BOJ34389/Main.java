import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class Main {
    private static int maxSwipes(String plan) {
        if (plan.startsWith("Marble")) return 19;
        return 14;
    }

    private static int maxMunch(String plan) {
        if (plan.endsWith("+")) return 350;
        return 200;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            String name = st.nextToken();
            String plan = st.nextToken();
            int usedSwipes = Integer.parseInt(st.nextToken());
            BigDecimal usedMunch = new BigDecimal(st.nextToken());
            
            int ms = maxSwipes(plan);
            int mm = maxMunch(plan);
            int remainingSwipes = ms - usedSwipes;

            BigDecimal remainingMunch = BigDecimal.valueOf(mm).subtract(usedMunch).setScale(2, RoundingMode.UNNECESSARY);
            boolean hasSwipes = remainingSwipes > 0;
            boolean hasMunch = remainingMunch.compareTo(BigDecimal.ZERO) > 0;

            String msg;
            if (hasSwipes && hasMunch) msg = "Use meal swipe or munch money";
            else if (!hasSwipes && hasMunch) msg = "Use munch money";
            else if (hasSwipes && !hasMunch) msg = "Use meal swipe";
            else msg = "Go to Downtown Golden!";

            sb.append(name).append(' ').append(remainingSwipes).append(' ').append(remainingMunch.toPlainString()).append(' ').append(msg);

            if (i + 1 < N) sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}