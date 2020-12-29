package num3036;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int[] b = new int[n - 1];
		for(int i = 0; i < n - 1; i++)
		{
			b[i] = Integer.parseInt(st.nextToken());
			System.out.println(getGiyak(a, b[i]));
		}
	}
	public static String getGiyak(int a, int b)
	{
		int gcd = 0;
		
		if(a > b)
		{
			for(int i = 1; i <= b; i++)
			{
				if((a % i == 0) && (b % i == 0)) {
					gcd = i;
				}
			}
			a /= gcd;
			b /= gcd;
			return a + "/" + b;
		}
		else if(a < b)
		{
			for(int i = 1; i <= a; i++)
			{
				if((a % i == 0) && (b % i == 0))
				{
					gcd = i;
				}
			}
			a /= gcd;
			b /= gcd;
			return a + "/" + b;
		}
		return "1/1";
	}
}
