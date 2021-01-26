package num9288;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= t; i++)
		{
			sb.append("Case " + i + ":").append('\n');
			int n = Integer.parseInt(br.readLine());
			for(int j = 1; j <= n / 2; j++)
			{
				if(j <= 6 && n - j <= 6)
				{
					sb.append("("+ j + "," + (n - j) + ")").append('\n');
				}
			}
		}
		
		System.out.println(sb);
	}
}
