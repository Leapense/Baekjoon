package num9298;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while(t-- > 0)
		{
			int n = Integer.parseInt(br.readLine());
			double[][] ants = new double[n][2];
			
			for(int i = 0; i < n; i++)
			{
				String[] input = br.readLine().split(" ");
				for(int j = 0; j < 2; j++)
				{
					ants[i][j] = Double.parseDouble(input[j]);
				}
			}
			double[] temp = new double[2];
			for(int i = 0; i < n - 1; i++)
			{
				if(ants[i][0] < ants[i + 1][0])
				{
					temp = ants[i];
					ants[i] = ants[i + 1];
					ants[i + 1] = temp;
				}
			}
			double x = ants[0][0] - ants[n - 1][0];
			
			for(int i = 0; i < n - 1; i++)
			{
				if(ants[i][1] < ants[i + 1][1])
				{
					temp = ants[i];
					ants[i] = ants[i + 1];
					ants[i + 1] = temp;
				}
			}
			double y = ants[0][1] - ants[n - 1][1];
			
			sb.append("Case " + (count + 1) + ": Area " + Math.scalb((x * y)/2, 1) + ", Perimeter " + Math.scalb((x * 2 + y * 2)/2, 1)).append('\n');
			count += 1;
		}
		System.out.println(sb);
	}
}
