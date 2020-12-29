package num4344;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		int sum = 0;
		int count = 0;
		for(int i = 0; i < t; i++)
		{
			
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int[] student = new int[n];
			for(int j = 0; j < n; j++)
			{
				student[j] = Integer.parseInt(st.nextToken());
				sum += student[j];
			}
			double avg = (double)sum / n;
			for(int j = 0; j < n; j++)
			{
				if(avg < student[j])
				{
					count += 1;
				}
			}
			System.out.printf("%.3f%%\n", (double)count / (double)n * 100.0);
			count = 0;
			sum = 0;
		}
	}
}
