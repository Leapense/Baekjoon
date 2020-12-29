package num1676;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		BigInteger n_fac = new BigInteger(getFac(n).toString());
		String n_fac_str = n_fac.toString();
		int count = 0;
		int i = n_fac_str.length() - 1;
		while(n_fac_str.charAt(i) == '0')
		{
			count++;
			i--;
		}
		System.out.println(count);
	}
	public static BigInteger getFac(int x)
	{
		BigInteger res = new BigInteger("1");
		if(x == 1 || x == 0)
		{
			return BigInteger.ONE;
		}
		else
		{
			for(int i = x; i >= 1; i--)
			{
				res = res.multiply(BigInteger.valueOf(i));
			}
		}
		return res;
	}
}
