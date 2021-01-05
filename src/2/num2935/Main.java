package num2935;

import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger a = new BigInteger(br.readLine());
		String op = br.readLine();
		BigInteger b = new BigInteger(br.readLine());
		if(op.equals("+"))
		{
			System.out.println(a.add(b));
		}
		else if(op.equals("*"))
		{
			System.out.println(a.multiply(b));
		}
	}
}
