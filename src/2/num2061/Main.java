package num2061;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		final int k = Integer.parseInt(st.nextToken());
		final int l = Integer.parseInt(st.nextToken());
		
		int rootk = (int)Math.sqrt(k);
		boolean[] sosu = new boolean[rootk + 1];
		Arrays.fill(sosu, true);
		for(int i = 2; i <= rootk; i++)
		{
			if(!sosu[i]) continue;
			
			if(k % i == 0)
			{
				if(i >= l)
				{
					System.out.println("GOOD");
					return;
				}
				else
				{
					System.out.println("BAD " + i);
					return;
				}
			}
			else
			{
				for(int j = 2; i * j <= rootk; j++)
				{
					sosu[i * j] = false;
				}
			}
		}
		System.out.println("BAD " + k);
	}
}
