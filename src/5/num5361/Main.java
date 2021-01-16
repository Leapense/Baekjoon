package num5361;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0)
		{
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			int d = Integer.parseInt(input[3]);
			int e = Integer.parseInt(input[4]);
			
			System.out.printf("$%.2f\n", (350.34 * (double)a) + (230.90 * (double)b) + (190.55 * (double)c) + (125.30 * (double)d) + (180.90 * (double)e));
		}
	}
}
