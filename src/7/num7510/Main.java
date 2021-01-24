package num7510;

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
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			sb.append("Scenario #"+i+":").append('\n');
			int max = Math.max(a, Math.max(b, c));
			if(max == a)
			{
				if(a * a == b * b + c * c) sb.append("yes").append('\n');
				else sb.append("no").append('\n');
			}
			else if(max == b)
			{
				if(b * b == a * a + c * c) sb.append("yes").append('\n');
				else sb.append("no").append('\n');
			}
			else if(max == c)
			{
				if(c * c == a * a + b * b) sb.append("yes").append('\n');
				else sb.append("no").append('\n');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
