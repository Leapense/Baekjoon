package num9524;

import java.util.Scanner;

public class Main {
	static int[] year = new int[] {1, 7, 2, 3};
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(year[sc.nextInt() - 1]);
		sc.close();
	}
}
