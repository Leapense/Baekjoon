import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().trim().split("\\s+");

        String a = parts[0];
        String b = parts[1];
        String c = parts[2];

        StringBuilder sb = new StringBuilder();
        sb.append(a);

        appendWithRule(sb, b);
        appendWithRule(sb, c);

        System.out.print(sb.toString());
    }

    private static void appendWithRule(StringBuilder sb, String next) {
        char last = sb.charAt(sb.length() - 1);
        char first = next.charAt(0);
        if (last == first) {
            sb.append('\'');
            sb.append(next.substring(1));
        } else {
            sb.append(next);
        }
    }
}