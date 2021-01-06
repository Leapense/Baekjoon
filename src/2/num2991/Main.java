package num2991;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int count;
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] input = new int[3];
		for(int i = 0; i < 3; i++)
		{
			count = 0;
			input[i] = Integer.parseInt(st.nextToken());
			if(input[i] % (a + b) <= a && input[i] % (a + b) != 0)
			{
				count += 1;
			}
			if(input[i] % (c + d) <= c && input[i] % (c + d) != 0)
			{
				count += 1;
			}
			sb.append(count).append('\n');
		}
		System.out.println(sb);
		
	}
}
