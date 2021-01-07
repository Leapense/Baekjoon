package num3058;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0)
		{
			int[] num = new int[7];
			int sum = 0;
			int min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 7; i++)
			{
				num[i] = Integer.parseInt(st.nextToken());
				if(num[i] % 2 == 0)
				{
					sum += num[i];
					if(min > num[i])
					{
						min = num[i];
					}
				}
			}
			System.out.printf("%d %d\n", sum, min);
		}
	}
}
