package num10951;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int a, b;
		while(sc.hasNext())
		{
			a = sc.nextInt();
			b = sc.nextInt();
			System.out.println(a + b);
		}
		sc.close();
	}
}
