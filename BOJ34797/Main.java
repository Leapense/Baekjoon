import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int gradeToPoints(char g) {
        switch (g) {
            case 'A':
                return 4;
            case 'B':
                return 3;
            case 'C':
                return 2;
            case 'D':
                return 1;
            case 'E':
                return 0;
            default:
                return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        int n = Integer.parseInt(br.readLine().trim());

        double sumPoints = 0.0;
        double bonusSum = 0.0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            char grade = s.charAt(0);
            char tier = s.charAt(1);

            int points = gradeToPoints(grade);
            sumPoints += points;

            if (grade == 'A' || grade == 'B' || grade == 'C') {
                if (tier == '1') bonusSum += 0.05;
                else if (tier == '2') bonusSum += 0.025;
            }
        }

        double unweighted = sumPoints / n;
        double weighted = unweighted + bonusSum;

        System.out.println(weighted);
    }
}
