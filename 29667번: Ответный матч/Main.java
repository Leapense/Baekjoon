import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] parseScore(String s) {
        String[] parts = s.trim().split(":");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new int[]{x, y};
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] first = parseScore(br.readLine());
        int[] second = parseScore(br.readLine());

        int a = first[0];
        int b = first[1];
        int c = second[0];
        int d = second[1];

        if (c <= a && d <= b) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}