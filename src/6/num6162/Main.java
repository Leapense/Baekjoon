package num6162;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int j = 1;
		while(test-- > 0)
		{
			String[] data = br.readLine().split(" ");
			double e = Double.parseDouble(data[0]);
			double a = Double.parseDouble(data[1]);
			double d = (e - 1.0) / a;
			sb.append("Data Set "+ j + ":").append('\n');
			
			if(e <= a)
			{
				sb.append("no drought").append('\n');
			}
			else
			{
				int exp = 0;
				while(Math.floor(d / 5.0) >= 1.0)
				{
					exp += 1;
					d /= 5.0;
				}
				sb.append("mega ".repeat(exp) + "drought").append('\n');
			}
			
			j += 1;
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
