package num5354;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int box_count = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(box_count-- > 0)
		{
			int box_size = Integer.parseInt(br.readLine());
			if(box_size < 3)
			{
				for(int i = 0; i < box_size; i++)
				{
					sb.append("#".repeat(box_size)).append('\n');
				}
				sb.append('\n');
			}
			else
			{
				sb.append("#".repeat(box_size)).append('\n');
				for(int i = 0; i < box_size - 2; i++)
				{
					sb.append("#").append("J".repeat(box_size - 2)).append("#").append('\n');
				}
				sb.append("#".repeat(box_size)).append('\n').append('\n');
			}
		}
		System.out.println(sb);
	}
}
