package num14623;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long a = Long.parseLong(br.readLine(), 2);
		long b = Long.parseLong(br.readLine(), 2);
		
		long ret = a * b;
		System.out.println(Long.toBinaryString(ret));
	}
}
