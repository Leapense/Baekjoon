package num10768;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int month = sc.nextInt();
		int day = sc.nextInt();
		
		if((month * 30.4167 + day) < (2 * 30.4167 + 18))
		{
			System.out.println("Before");
		}
		else if((month * 30.4167 + day) > (2 * 30.4167 + 18))
		{
			System.out.println("After");
		}
		else
		{
			System.out.println("Special");
		}
	}
}
