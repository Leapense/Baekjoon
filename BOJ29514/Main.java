import java.io.*;
import java.nio.charset.StandardCharsets;
public class Main {
    private static boolean isLatinLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isUpper(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    private static char toUpper(char c) {
        if (c >= 'a' && c <= 'z') return (char) (c - 'a' + 'A');
        return c;
    }

    private static char toLower(char c) {
        if (c >= 'A' && c <= 'Z') return (char) (c - 'A' + 'a');
        return c;
    }

    private static String readAllStdin() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1 << 14];
        int r;
        while ((r = System.in.read(buf)) != -1) {
            baos.write(buf, 0, r);
        }

        String s = baos.toString(StandardCharsets.UTF_8);
        while (!s.isEmpty()) {
            char last = s.charAt(s.length() - 1);
            if (last == '\n' || last == '\r') s = s.substring(0, s.length() - 1);
            else break;
        }

        return s;
    }

    public static void main(String[] args) throws Exception {
        String s = readAllStdin();
        if (s == null) return;

        long costStartUpper = 0;
        long costStartLower = 0;

        int letterIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isLatinLetter(c)) continue;

            boolean origUpper = isUpper(c);
            boolean desiredUpperIfStartUpper = (letterIndex % 2 == 0);
            boolean desiredUpperIfStartLower = (letterIndex % 2 == 1);

            if (origUpper != desiredUpperIfStartUpper) costStartUpper++;
            if (origUpper != desiredUpperIfStartLower) costStartLower++;

            letterIndex++;
        }

        boolean startUpper = (costStartUpper <= costStartLower);
        StringBuilder out = new StringBuilder(s.length());
        letterIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isLatinLetter(c)) {
                out.append(c);
            } else {
                boolean desiredUpper = startUpper ? (letterIndex % 2 == 0) : (letterIndex % 2 == 1);

                out.append(desiredUpper ? toUpper(c) : toLower(c));
                letterIndex++;
            }
        }

        System.out.print(out.toString());
    }
}