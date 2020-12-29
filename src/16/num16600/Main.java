package num16600;

import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger x = new BigInteger(br.readLine());
		
		System.out.println(x.sqrt().multiply(new BigInteger("4")).doubleValue());
	}
}
