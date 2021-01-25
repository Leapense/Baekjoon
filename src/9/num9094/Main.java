package num9094;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			int count = 0;
			
			for(int a = 1; a < n; a++)
			{
				for(int b = 1; b < n; b++)
				{
					if(a < b && b < n && (a * a + b * b + m) % (a * b) == 0)
					{
						count++;
					}
				}
			}
			System.out.println(count);
		}
	}
}
