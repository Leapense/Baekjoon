import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        String s = br.readLine();
        if (s == null) return;

        StringBuilder out = new StringBuilder();
        for (int k = 0; k < 26; k++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    line.append(' ');
                } else {
                    int shifted = (c - 'A' - k + 26) % 26;
                    line.append((char) ('A' + shifted));
                }
            }
            out.append(line).append('\n');
        }
        System.out.print(out.toString());
    }
}
