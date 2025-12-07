import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int N = sc.nextInt();
            int Q = sc.nextInt();
            if (N == 0 && Q == 0) {
                break;
            }

            String[] songs = new String[N];
            for (int i = 0; i < N; i++) {
                songs[i] = sc.next();
            }
            for (int i = 0; i < Q; i++) {
                int k = sc.nextInt();
                System.out.println(findSong(N, songs, k));
            }

            System.out.println();
        }

        sc.close();
    }

    private static String findSong(int N, String[] songs, int k) {
        long currentLength = 1;
        long count = 0;
        long numWords = 0;

        long powerOfN = N;

        while (true) {
            count = currentLength * powerOfN;
            if (k <= count) {
                break;
            }
            k -= count;
            currentLength++;

            if (N > 1) {
                powerOfN *= N;
            }
        }

        long wordIndex = (k - 1) / currentLength;
        long charIndex = (k - 1) % currentLength;

        long targetPower = 1;
        for (int i = 0; i < currentLength - 1 - charIndex; i++) {
            targetPower *= N;
        }

        int songIndex = (int) ((wordIndex / targetPower) % N);

        return songs[songIndex];
    }
}