package num1975;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			int n = Integer.parseInt(br.readLine());
			int count = 0;
			for(int j = 2; j <= n; j++)
			{
				int temp = n;
				while(temp % j == 0)
				{
					count++;
					temp /= j;
				}
			}
			sb.append(count).append('\n');
		}
		System.out.println(sb);
	}
}
