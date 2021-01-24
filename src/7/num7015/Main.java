package num7015;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		while(n-- > 0)
		{
			String[] input = br.readLine().split(" ");
			int y = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			int d = Integer.parseInt(input[2]);
			int count = (y % 3 == 0 ? 20 : 19) - d + 1;
			
			for(int i = m; i <= 9; i++)
			{
				count += y % 3 == 0 ? 20 : i % 2 == 1 ? 20 : 19;
			}
			for(int i = y + 1; i <= 999; i++)
			{
				count += i % 3 != 0 ? 195 : 200;
			}
			
			System.out.println(count);
		}
	}
}
