import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static String transform(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char)('a' + (arr[i] - 'a' + 13) % 26);
        }
        return new String(arr);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String action = br.readLine();
        int n = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            out.append(transform(s)).append('\n');
        }

        System.out.print(out);
    }
}