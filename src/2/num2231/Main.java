package num2231;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		boolean found = false;
		
		for(int i = 0; i < n; i++)
		{
			if(is_generator(i) == n)
			{
				System.out.println(i);
				found = true;
				break;
			}
		}
		if(!found)
		{
			System.out.println(0);
		}
	}
	private static int is_generator(int n)
	{
		int digit = 1;
		int temp = n;
		while(temp > 0)
		{
			temp /= 10;
			digit++;
		}
		int answer = n;
		for(int i = 0; i < digit; i++)
		{
			answer += n % 10;
			n /= 10;
		}
		
		return answer;
	}
}
