import java.io.*;
import java.util.*;

public class Main {
    private static class Info {
        int count;
        String name;
        Info(int count, String name) {
            this.count = count;
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        Map<String, Info> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            int sp = line.indexOf(' ');
            String first = line.substring(0, sp);
            String last = line.substring(sp + 1);

            String key = "" + first.charAt(0) + last.charAt(0);

            Info info = map.get(key);
            if (info == null) {
                map.put(key, new Info(1, line));
            } else {
                info.count++;
                info.name = null;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            String key = br.readLine().trim();

            Info info = map.get(key);
            if (info == null) {
                sb.append("nobody\n");
            } else if (info.count == 1) {
                sb.append(info.name).append('\n');
            } else {
                sb.append("ambiguous\n");
            }
        }

        System.out.print(sb.toString());
    }
}