import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }
        int[] others = new int[n - 1];
        int[] baseCount = new int[110];
        for (int i = 0; i < n - 1; i++) {
            others[i] = sc.nextInt();
            baseCount[others[i]]++;
        }

        int bestNum = 1;
        int bestPlayerCount = -1;

        for (int petyaNum = 1; petyaNum <= 102; petyaNum++) {
            int[] count = baseCount.clone();
            count[petyaNum]++;

            int minUnique = -1;
            for (int j = 1; j <= 102; j++) {
                if (count[j] == 1) {
                    minUnique = j;
                    break;
                }
            }

            int petyaScore = scores[n - 1];
            int[] otherScores = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                otherScores[i] = scores[i];
            }

            if (minUnique != -1) {
                if (petyaNum == minUnique) {
                    petyaScore += minUnique;
                } else {
                    for (int i = 0; i < n - 1; i++) {
                        if (others[i] == minUnique) {
                            otherScores[i] += minUnique;
                            break;
                        }
                    }
                }
            }
            int countFewer = 0;
            for (int i = 0; i < n - 1; i++) {
                if (otherScores[i] < petyaScore) {
                    countFewer++;
                }
            }

            if (countFewer > bestPlayerCount) {
                bestPlayerCount = countFewer;
                bestNum = petyaNum;
            }
        }

        System.out.println(bestNum);
    }
}