package num15921;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n == 0)
		{
			System.out.println("divide by zero");
		}
		else
		{
			int[] x = new int[n];
			int sum = 0;
			StringTokenizer st=  new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
			{
				x[i] = Integer.parseInt(st.nextToken());
				sum += x[i];
			}
			if(sum == 0)
			{
				System.out.println("divide by zero");
			}
			else
			{
				System.out.println("1.00");
			}
		}
	}
}
