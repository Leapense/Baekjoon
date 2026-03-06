import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        List<String> apps = new ArrayList<>();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            if (line.startsWith("Run ")) {
                String appName = line.substring(4);
                apps.add(0, appName);
                out.append(appName).append('\n');
            } else {
                int k = (line.length() - 3) / 4;
                int index = k % apps.size();

                String active = apps.remove(index);
                apps.add(0, active);
                out.append(active).append('\n');
            }
        }

        System.out.print(out.toString());
    }
}