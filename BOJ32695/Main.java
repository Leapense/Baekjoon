import java.io.*;
import java.util.*;

public class Main {
    private static String fmt(double x) {
        String s = String.format("%.10f", x);
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == '0') i--;
        if (i >= 0 && s.charAt(i) == '.') i--;
        return s.substring(0, i + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();

        StringTokenizer st = new StringTokenizer(line);
        double A = Double.parseDouble(st.nextToken());
        double B = Double.parseDouble(st.nextToken());
        double C = Double.parseDouble(st.nextToken());
        double D = Double.parseDouble(st.nextToken());

        double eKeepWarm = 60.0 * C;
        double x = (3600.0 * D) / (60.0 + D * B);
        x = Math.min(78.0, x);
        double eReheat = A * x;

        System.out.println(fmt(eKeepWarm));
        System.out.print(fmt(eReheat));
    }
}