package num6491;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < input.length - 1; i++)
		{
			int sum = 0;
			int n = Integer.parseInt(input[i]);
			
			for(int j = 1; j < n; j++)
			{
				if(n % j == 0)
				{
					sum += j;
				}
			}
			if(sum > n) System.out.println(n + " ABUNDANT");
			else if(sum < n) System.out.println(n + " DEFICENT");
			else System.out.println(n + " PERFECT");
		}
	}
}
