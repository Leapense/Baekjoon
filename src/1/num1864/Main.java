package num1864;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			s = br.readLine();
			if(s.equals("#"))
			{
				break;
			}
			else
			{
				int str_length = s.length();
				int total = 0;
				for(; str_length > 0; str_length--)
				{
					int num = 0;
					switch(s.charAt(s.length() - str_length))
					{
					case '-':
						num = 0;
						break;
					case '\\':
						num = 1;
						break;
					case '(':
						num = 2;
						break;
					case '@':
						num = 3;
						break;
					case '?':
						num = 4;
						break;
					case '>':
						num = 5;
						break;
					case '&':
						num = 6;
						break;
					case '%':
						num = 7;
						break;
					case '/':
						num = -1;
						break;
					}
					total += (num * Math.pow(8, str_length - 1));
				}
				sb.append(total).append('\n');
			}
		}
		System.out.println(sb);
	}
}
