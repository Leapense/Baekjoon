package num9310;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n == 0)
			{
				break;
			}
			else
			{
				String[] input = br.readLine().split(" ");
				int a1 = Integer.parseInt(input[0]);
				int a2 = Integer.parseInt(input[1]);
				int a3 = Integer.parseInt(input[2]);
				
				if(a2 * 2 == a1 + a3)
				{
					int d = a2 - a1;
					int an = n * (2 * a1 + (n - 1) * d) / 2;
					System.out.println(an);
				}
				else
				{
					int r = a2 / a1;
					int an = a1 * (int)((Math.pow(r, n) - 1) / (r - 1));
					System.out.println(an);
				}
			}
		}
	}
}
