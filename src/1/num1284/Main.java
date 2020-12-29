package num1284;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			s = br.readLine();
			if(s.equals("0"))
			{
				break;
			}
			int sum = s.length() + 1;
			for(int i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == '1') sum += 2;
				else if(s.charAt(i) == '0') sum += 4;
				else sum += 3;
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
}
