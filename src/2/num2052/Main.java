package num2052;

import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		BigInteger a = new BigInteger("2");
		a = a.pow(n);
		BigInteger b = new BigInteger("10");
		System.out.print("0.");
		while(true)
		{
			if(b.mod(a).compareTo(BigInteger.ZERO) == 0)
			{
				break;
			}
			else
			{
				System.out.print(b.divide(a));
				
				b = b.subtract((b.divide(a)).multiply(a));
				b = b.multiply(new BigInteger("10"));
			}
		}
		System.out.println(b.divide(a));
	}
}
