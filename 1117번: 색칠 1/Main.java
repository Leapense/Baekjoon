import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long overlap(long a1, long a2, long b1, long b2) {
        long left = Math.max(a1, b1);
        long right = Math.min(a2, b2);
        return Math.max(0L, right - left);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long W = Long.parseLong(st.nextToken());
        long H = Long.parseLong(st.nextToken());
        long f = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        long L = f;
        long R = W - f;

        long Xw = Math.max(L, R);
        long doubleW = Math.min(L, R);

        long lenD = overlap(x1, x2, 0, doubleW);
        long lenS = overlap(x1, x2, doubleW, Xw);

        long Yh = H / (c + 1);
        long hlen = overlap(y1, y2, 0, Yh);
        long painted = (c + 1) * (lenS + 2 * lenD) * hlen;
        
        long total = W * H;
        long ans = total - painted;

        System.out.println(ans);
    }
}