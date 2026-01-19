import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(readNonEmptyLine(br));

        for (int tc = 0; tc < T; tc++) {
            int X = Integer.parseInt(readNonEmptyLine(br));
            String expr = readNonEmptyLine(br);

            StringTokenizer st = new StringTokenizer(expr);

            boolean many = false;
            int sum = 0;

            int operands = X + 1; // 피연산자 개수
            for (int i = 0; i < operands; i++) {
                String tok = st.nextToken(); // 숫자 또는 "!"
                if (tok.equals("!")) {
                    many = true;
                } else {
                    // tok는 "1"~"9"만 온다
                    sum += tok.charAt(0) - '0';
                }

                if (i < operands - 1) {
                    st.nextToken(); // '+' 토큰은 버린다
                }
            }

            if (many || sum >= 10) sb.append("!\n");
            else sb.append(sum).append('\n');
        }

        System.out.print(sb.toString());
    }

    // 혹시 모를 공백 줄 방지용 (온라인 저지에서 가끔 입력에 빈 줄이 끼는 경우 대비)
    private static String readNonEmptyLine(BufferedReader br)
        throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        return "";
    }
}
