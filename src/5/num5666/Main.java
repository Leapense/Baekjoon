package num5666;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			try {
				String[] input = br.readLine().split(" ");
				int h = Integer.parseInt(input[0]);
				int p = Integer.parseInt(input[1]);
				System.out.printf("%.2f\n", (double)h / (double)p);
			}
			catch(Exception e)
			{
				break;
			}
		}
	}
}
