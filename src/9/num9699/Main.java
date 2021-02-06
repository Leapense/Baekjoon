package num9699;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			int max = 0;
			int[] rice_sack = new int[5];
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 5; j++)
			{
				rice_sack[j] = Integer.parseInt(input[j]);
			}
			for(int j = 0; j < 5; j++)
			{
				if(max < rice_sack[j])
				{
					max = rice_sack[j];
				}
			}
			System.out.println("Case #" + (i + 1) + ": " + max);
		}
	}
}
