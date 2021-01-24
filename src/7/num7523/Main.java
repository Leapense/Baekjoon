package num7523;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= t; i++)
		{
			String[] input = br.readLine().split(" ");
			sb.append("Scenario #" + i + ":").append('\n');
			long n = Long.parseLong(input[0]);
			long m = Long.parseLong(input[1]);
			
			long result_m = (long)((m * (m + 1)) / 2);
			long result_n = (long)((n * (n - 1)) / 2);
			
			sb.append(result_m - result_n).append('\n').append('\n');
		}
		System.out.println(sb);
	}
}
