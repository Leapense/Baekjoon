package num9297;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= t; i++)
		{
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			if(a % b == 0)
			{
				sb.append("Case " + i + ": " + (a / b)).append('\n');
			}
			else
			{
				if(a / b == 0)
				{
					sb.append("Case " + i + ": " + (a % b) + "/" + b).append('\n');
				}
				else
				{
					sb.append("Case " + i + ": " + (a / b) + " " + (a % b) + "/" + b).append('\n');
				}
			}
		}
		System.out.println(sb);
	}
}
