import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int wall = 0;
        int oneVirus = 0;
        int allVirus = 0;
        int vaccine = 0;
        int start = 0;
        int exit = 0;

        for (int i = 0; i < N; i++) {
            String row = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                char c = row.charAt(j);
                switch(c) {
                    case '#':
                        wall++;
                        break;
                    case 'U':
                    case 'D':
                    case 'L':
                    case 'R':
                        oneVirus++;
                        break;
                    case 'A':
                        allVirus++;
                        break;
                    case 'V':
                        vaccine++;
                        break;
                    case 'S':
                        start++;
                        break;
                    case 'E':
                        exit++;
                        break;
                    default:
                        break;
                }
            }
        }

        if (start != 1 || exit != 1) {
            System.out.println(-1);
            return;
        }

        boolean p1 = (wall <= 1) && (oneVirus <= 1) && (allVirus == 0) && (vaccine == 0);
        boolean p2 = (allVirus == 0) && (vaccine == 0);
        boolean p3 = (allVirus == 0);

        if (p1) System.out.println(1);
        else if (p2) System.out.println(2);
        else if (p3) System.out.println(3);
        else System.out.println(4);
    }
}