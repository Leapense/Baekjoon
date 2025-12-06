import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        int[][] sumJ = new int[M + 1][N + 1];
        int[][] sumO = new int[M + 1][N + 1];
        int[][] sumI = new int[M + 1][N + 1];
        
        for (int i = 1; i <= M; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                char terrain = line.charAt(j - 1);

                int isJ = (terrain == 'J') ? 1 : 0;
                int isO = (terrain == 'O') ? 1 : 0;
                int isI = (terrain == 'I') ? 1 : 0;

                sumJ[i][j] = sumJ[i - 1][j] + sumJ[i][j - 1] - sumJ[i - 1][j - 1] + isJ;
                sumO[i][j] = sumO[i - 1][j] + sumO[i][j - 1] - sumO[i - 1][j - 1] + isO;
                sumI[i][j] = sumI[i - 1][j] + sumI[i][j - 1] - sumI[i - 1][j - 1] + isI;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int countJ = sumJ[r2][c2] - sumJ[r1 - 1][c2] - sumJ[r2][c1 - 1] + sumJ[r1 - 1][c1 - 1];
            int countO = sumO[r2][c2] - sumO[r1 - 1][c2] - sumO[r2][c1 - 1] + sumO[r1 - 1][c1 - 1];
            int countI = sumI[r2][c2] - sumI[r1 - 1][c2] - sumI[r2][c1 - 1] + sumI[r1 - 1][c1 - 1];
            sb.append(countJ).append(" ")
              .append(countO).append(" ")
              .append(countI).append("\n");
        }

        System.out.println(sb);
    }
}