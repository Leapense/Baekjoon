package num10179;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		double m;
		while(t-- > 0)
		{
			m = sc.nextDouble();
			System.out.printf("$%.2f\n", (m - (m * 0.2)));
		}
	}
}
