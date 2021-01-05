package num2975;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String status = br.readLine();
			if(status.equals("0 W 0"))
			{
				break;
			}
			else
			{
				StringTokenizer st = new StringTokenizer(status, " ");
				int a = Integer.parseInt(st.nextToken());
				String op = st.nextToken();
				int b = Integer.parseInt(st.nextToken());
				if(op.equals("W"))
				{
					if(a - b < -200)
					{
						sb.append("Not allowed").append('\n');
					}
					else
					{
						sb.append(a - b).append('\n');
					}
				}
				else if(op.equals("D"))
				{
					sb.append(a + b).append('\n');
				}
			}
		}
		System.out.println(sb);
	}
}
