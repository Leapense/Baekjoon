package num1247;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++)
		{
			int n = Integer.parseInt(br.readLine());
			long sum = 0;
			for(int j = 0; j < n; j++)
			{
				long temp = Long.parseLong(br.readLine());
				sum += temp;
			}
			
			if(sum > 0) sb.append('+').append('\n');
			else if(sum < 0) sb.append('-').append('\n');
			else sb.append('0').append('\n');
		}
		System.out.println(sb);
	}
}
