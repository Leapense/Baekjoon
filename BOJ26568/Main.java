import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int n = readNextNonEmptyIntLine(br);
        for (int tc = 0; tc < n; tc++) {
            int w = readNextNonEmptyIntLine(br);
            int m = readNextNonEmptyIntLine(br);

            List<String> words = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                String line = br.readLine();
                if (line == null) line = "";
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                for (String p : parts) {
                    if (!p.isEmpty()) words.add(p);
                }
            }

            StringBuilder cur = new StringBuilder();
            for (String word : words) {
                if (cur.length() == 0) {
                    cur.append(word);
                } else {
                    int newLen = cur.length() + 1 + word.length();
                    if (newLen <= w) {
                        cur.append(' ').append(word);
                    } else {
                        out.append(cur).append('\n');
                        cur.setLength(0);
                        cur.append(word);
                    }
                }
            }

            if (cur.length() > 0) {
                out.append(cur).append('\n');
            }

            out.append('\n');
        }

        System.out.print(out.toString());
    }

    private static int readNextNonEmptyIntLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                return Integer.parseInt(line);
            }
        }
        throw new EOFException("Unexpected end of input");
    }
}