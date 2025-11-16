import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int simulation = 1;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.equals("0")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }

            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            String seq = st.nextToken();

            System.out.println("Simulation " + simulation);
            simulation++;

            List<Character> cache = new LinkedList<>();

            char[] arr = seq.toCharArray();
            for (char ch : arr) {
                if (ch == '!') {
                    StringBuilder sb = new StringBuilder();
                    for (char c : cache) {
                        sb.append(c);
                    }
                    System.out.println(sb.toString());
                } else {
                    if (cache.contains(ch)) {
                        cache.remove((Character) ch);
                        cache.add(ch);
                    } else {
                        if (cache.size() == N) {
                            cache.remove(0);
                        }
                        cache.add(ch);
                    }
                }
            }
        }
    }
}