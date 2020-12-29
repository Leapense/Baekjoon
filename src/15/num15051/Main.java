package num15051;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] n = new int[3];
		int times = 0, mintime = 0;
		for(int i = 0; i < 3; i++)
		{
			n[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				times += (n[j] * Math.abs(j - i)  * 2);
			}
			if(i == 0)
			{
				mintime = times;
			}
			else if(times < mintime)
			{
				mintime = times;
			}
			times = 0;
		}
		
		System.out.println(mintime);
	}
}
