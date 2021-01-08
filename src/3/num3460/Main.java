package num3460;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0)
		{
			int n = Integer.parseInt(br.readLine());
			for(int i = 0; i < 30; i++)
			{
				if((n & (1 << i)) != 0)
				{
					sb.append(i).append(' ');
				}
			}
		}
		System.out.println(sb);
	}
}
