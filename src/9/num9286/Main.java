package num9286;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = Integer.parseInt(br.readLine());
		for(int i = 1; i <= count; i++)
		{
			sb.append("Case " + i + ":").append('\n');
			int t = Integer.parseInt(br.readLine());
			for(int j = 0; j < t; j++)
			{
				int grade = Integer.parseInt(br.readLine());
				if(grade < 6)
				{
					sb.append(Integer.toString(grade + 1)).append('\n');
				}
			}
		}
		System.out.println(sb);
	}
}
