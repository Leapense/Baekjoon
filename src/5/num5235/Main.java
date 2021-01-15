package num5235;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t-- > 0)
		{
			int even_sum = 0, odd_sum = 0;
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			String[] numArr = new String[n];
			for(int i = 1; i < n + 1; i++)
			{
				numArr[i - 1] = input[i];
			}
			for(int i = 0; i < n; i++)
			{
				int a = Integer.parseInt(numArr[i]);
				if(a % 2 == 0)
				{
					even_sum += a;
				}
				else
				{
					odd_sum += a;
				}
			}
			
			if(even_sum < odd_sum)
			{
				sb.append("ODD").append('\n');
			}
			else if(even_sum > odd_sum)
			{
				sb.append("EVEN").append('\n');
			}
			else
			{
				sb.append("TIE").append('\n');
			}
		}
		System.out.println(sb);
	}
}
