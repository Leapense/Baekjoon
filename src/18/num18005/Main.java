package num18005;

import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger n = new BigInteger(br.readLine());
		
		if(n.mod(new BigInteger("2")).compareTo(new BigInteger("1")) == 0)
		{
			System.out.println("0");
		}
		else if(n.divide(new BigInteger("2")).mod(new BigInteger("2")).compareTo(new BigInteger("0")) == 0)
		{
			System.out.println("2");
		}
		else
		{
			System.out.println("1");
		}
	}
}
