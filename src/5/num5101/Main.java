package num5101;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String input = br.readLine();
			if(input.equals("0 0 0")) {
				break;
			}
			else {
				String[] st = input.split(" ");
				int a = Integer.parseInt(st[0]);
				int b = Integer.parseInt(st[1]);
				int c = Integer.parseInt(st[2]);
				if((c - a) % b == 0 && ((b > 0 && c >= a) || (b < 0 && c <= a)))
				{
					sb.append((int)((c - a) / b + 1)).append('\n');
				}
				else
				{
					sb.append("X").append('\n');
				}
			}
		}
		System.out.println(sb);
		
	}
}
