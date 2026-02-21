import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        String lower = s.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }

    private static String extractDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line1 = br.readLine();
        String line2 = br.readLine();
        String line3 = br.readLine();

        String digits1 = extractDigits(line1);
        String oib = digits1.substring(digits1.length() - 11);

        String yy = line2.substring(0, 2);
        String mm = line2.substring(2, 4);
        String dd = line2.substring(4, 6);

        int year2 = Integer.parseInt(yy);
        int fullYear = (year2 <= 24) ? (2000 + year2) : (1900 + year2);
        String birth = dd + "-" + mm + "-" + fullYear;

        int sep = line3.indexOf("<<");
        String firstUpper = (sep >= 0) ? line3.substring(0, sep) : line3;
        String rest = (sep >= 0) ? line3.substring(sep + 2) : "";

        int cut = rest.indexOf('<');
        String lastUpper = (cut >= 0) ? rest.substring(0, cut) : rest;

        String firstName = capitalize(firstUpper);
        String lastName = capitalize(lastUpper);

        System.out.println("Ime: " + firstName);
        System.out.println("Prezime: " + lastName);
        System.out.println("Datum rodjenja: " + birth);
        System.out.println("OIB: " + oib);
    }
}