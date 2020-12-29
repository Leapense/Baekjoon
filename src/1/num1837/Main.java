package num1837;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BigInteger p, k;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		p = new BigInteger(st.nextToken());
		k = new BigInteger(st.nextToken());
		
		BigInteger i = new BigInteger("2");
		for(; (i.compareTo(k)) < 0; i = i.add(BigInteger.ONE))
		{
			if(p.mod(i).compareTo(BigInteger.ZERO) == 0)
			{
				System.out.println("BAD " + i);
				break;
			}
		}
		
		if(i.compareTo(k) >= 0) System.out.println("GOOD");
	}
}
