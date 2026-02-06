import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();

        int[] ids = new int[N];
        int[] wants = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = sc.nextInt();
            wants[i] = sc.nextInt();
        }

        for (int i = 0; i < N - 1; i++) {
            if (ids[i] == ids[i + 1]) {
                continue;
            }

            int largerHolder, smallerHolder;
            if (ids[i] > ids[i + 1]) {
                largerHolder = i;
                smallerHolder = i + 1;
            } else {
                largerHolder = i + 1;
                smallerHolder = i;
            }

            if (wants[largerHolder] == 1 && wants[smallerHolder] == 0) {
                int temp = ids[i];
                ids[i] = ids[i + 1];
                ids[i + 1] = temp;
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            boolean willBeStuffed = (ids[i] <= X);
            boolean wantsStuffed = (wants[i] == 1);

            if (willBeStuffed == wantsStuffed) {
                count++;
            }
        }

        System.out.println(count);
    }
}