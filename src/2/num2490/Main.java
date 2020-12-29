package num2490;

import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int zero_cnt, one_cnt;
		int status;
		for(int i = 0; i < 3; i++)
		{
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			zero_cnt = 0;
			one_cnt = 0;
			while(st.hasMoreTokens())
			{
				status = Integer.parseInt(st.nextToken());
				if(status == 0)
				{
					zero_cnt += 1;
				}
				else if(status == 1)
				{
					one_cnt += 1;
				}
			}
			if(zero_cnt == 1 && one_cnt == 3)
			{
				sb.append("A").append('\n');
			}
			else if(zero_cnt == 2 && one_cnt == 2)
			{
				sb.append("B").append('\n');
			}
			else if(zero_cnt == 3 && one_cnt == 1)
			{
				sb.append("C").append('\n');
			}
			else if(zero_cnt == 4)
			{
				sb.append("D").append('\n');
			}
			else if(one_cnt == 4)
			{
				sb.append("E").append('\n');
			}
		}
		System.out.println(sb);
		
	}
}
