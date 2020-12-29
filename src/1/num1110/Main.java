package num1110;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		int count = 0;
		
		int a = input / 10;
		int b = input % 10;
		
		while(true)
		{
			int sum = a + b;
			a = b;
			if(sum >= 10)
				b = sum % 10;
			else
				b = sum;
			
			count += 1;
			if(10 * a + b == input)
				break;
		}
		System.out.println(count);
		sc.close();
	}
}
