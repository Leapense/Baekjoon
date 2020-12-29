package num5086;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n, m;
		
		while(true)
		{
			n = sc.nextInt();
			m = sc.nextInt();
			if(n == m || n == 0 && m == 0)
			{
				break;
			}
			else
			{
				if(m % n == 0) System.out.println("factor");
				else if(n % m == 0) System.out.println("multiple");
				else System.out.println("neither");
			}
		}
	}
}
