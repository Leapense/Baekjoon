package num11050;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		int tmp = n - k;
		if((1 <= n && n <= 10) &&(0 <= k && k <= n))
		{
			n = getFactoriel(n);
			k = getFactoriel(k);
			tmp = getFactoriel(tmp);
			
			System.out.println((int)(n /(k * tmp)));
		}
		else
		{
			System.out.println("0");
		}
	}
	static int getFactoriel(int n)
	{
		if(n == 0)
		{
			n = 1;
		}
		else
		{
			for(int i = n - 1; i >= 1; i--)
			{
				n *= i;
			}
		}
		return n;
	}
}
