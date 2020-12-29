package num1037;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int num = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++)
		{
			num = sc.nextInt();
			
			if(num > max) max = num;
			if(num < min) min = num;
		}
		System.out.println(min * max);
	}
}
