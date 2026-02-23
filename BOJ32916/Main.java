import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(System.out);

        int lower = Integer.parseInt(st.nextToken());
        int upper = Integer.parseInt(st.nextToken());

        int ans;
        if ((lower & 1) == 1) {
            ans = upper;
        } else {
            ans = upper & ~1;
        }

        out.println(ans);

        out.flush();
        out.close();
    }
}