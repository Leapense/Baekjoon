import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String simplify(String s) {
        if (s == null) {
            return "";
        }

        StringBuilder out = new StringBuilder(s.length());
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == 'c') {
                if (i + 1 < n && s.charAt(i + 1) == 'h') {
                    out.append('c');
                    i++;
                } else if (i + 1 < n) {
                    char next = s.charAt(i + 1);
                    if (next == 'e' || next == 'i' || next == 'y') {
                        out.append('s');
                    } else {
                        out.append('k');
                    }
                } else {
                    out.append('k');
                }
            } else {
                out.append(cur);
            }
        }

        return out.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String countLine = br.readLine();

        if (countLine == null) {
            return;
        }

        int n = Integer.parseInt(countLine.trim());
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (s == null) {
                break;
            }

            ans.append(simplify(s.trim())).append('\n');
        }

        System.out.print(ans);
    }
}