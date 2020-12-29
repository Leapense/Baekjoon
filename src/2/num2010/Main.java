package num2010;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] com = new int[n];
		int sum = 0;
		for(int i = 0; i < n; i++)
		{
			com[i] = Integer.parseInt(br.readLine());
			sum += com[i];
		}
		
		System.out.println(sum - n + 1);
	}
}
