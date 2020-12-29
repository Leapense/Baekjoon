package num16428;

import java.io.*;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		BigInteger a = new BigInteger(st.nextToken());
		BigInteger b = new BigInteger(st.nextToken());
		BigInteger div = a.divide(b);
		BigInteger mod = a.mod(b);
		if(b.compareTo(new BigInteger("0")) < 0 && a.compareTo(new BigInteger("0")) != 0)
		{
			div = div.add(new BigInteger("1"));
			mod.subtract(b);
		}
		System.out.println(div);
		System.out.println(mod);
	}
}
