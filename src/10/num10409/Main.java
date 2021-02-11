package num10409;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int t = Integer.parseInt(input[1]);
		
		int sum = 0;
		int completed_count = 0;
		String[] fcfs = br.readLine().split(" ");
		for(int i = 0; i < n; i++)
		{
			int time = Integer.parseInt(fcfs[i]);
			sum += time;
			if(sum <= t)
			{
				completed_count += 1;
			}
		}
		System.out.println(completed_count);
	}
}
