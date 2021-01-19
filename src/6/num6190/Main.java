package num6190;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		int score = 0;
		while(n != 1)
		{
			if(n % 2 == 0)
			{
				n /= 2;
				score += 1;
			}
			else if(n % 2 != 0)
			{
				n = 3 * n + 1;
				score += 1;
			}
		}
		System.out.println(score);
	}
}
