package num11051;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		System.out.println((factorial(n) * mod_inverse((factorial(n - k) * factorial(k)) % 10007, 10005)) % 10007 );
	}
	static int factorial(int n)
	{
		if(n <= 1) {
			return 1;
		}
		return n * factorial(n - 1) % 10007;
	}
	static int mod_inverse(int a, int p)
	{
		int ret = 1;
		while(p > 0)
		{
			if(p % 2 == 1)
			{
				ret *= a;
				p--;
				ret %= 10007;
			}
			a *= a;
			a %= 10007;
			p >>= 1;
		}
		return ret;
	}
}
