package num7595;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int star = Integer.parseInt(br.readLine());
			if(star == 0)
			{
				break;
			}
			else
			{
				for(int i = 1; i <= star; i++)
				{
					sb.append("*".repeat(i)).append('\n');
				}
			}
		}
		System.out.println(sb);
	}
}
