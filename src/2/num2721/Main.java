package num2721;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int sum;
		for(int i = 0; i < t; i++)
		{
			sum = 0;
			int n = Integer.parseInt(br.readLine());
			for(int j = 1; j <= n; j++)
			{
				sum += ((j * (j + 1) * (j + 2)) / 2);
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
