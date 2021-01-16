package num5523;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int round_cnt = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int a_count = 0, b_count = 0;
		while(round_cnt-- > 0)
		{
			String[] score_input = br.readLine().split(" ");
			int a_score = Integer.parseInt(score_input[0]);
			int b_score = Integer.parseInt(score_input[1]);
			
			if(a_score > b_score)
			{
				a_count += 1;
			}
			else if(a_score < b_score)
			{
				b_count += 1;
			}
			else
			{
				continue;
			}
		}
		sb.append(a_count + " " + b_count).append('\n');
		System.out.println(sb);
	}
}
