package num2748;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n + 1];
		StringBuilder sb = new StringBuilder();
		arr[0] = 0;
		arr[1] = 1;
		
		if(n <= 1)
		{
			sb.append(arr[n]).append('\n');
		}
		else
		{
			for(int i = 2; i <= n; i++)
			{
				arr[i] = arr[i - 2] + arr[i - 1];
			}
			sb.append(arr[n]).append('\n');
		}
		System.out.println(sb);
	}
}
