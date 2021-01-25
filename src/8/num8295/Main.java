package num8295;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int p = Integer.parseInt(input[2]);
		int count = 0;
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				if((i + j + 2) * 2 >= p)
				{
					count += (n - i) * (m - j);
				}
			}
		}
		System.out.println(count);
	}
}
