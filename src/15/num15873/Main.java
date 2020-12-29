package num15873;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int length = s.length();
		int res = 0;
		
		if(length == 2)
		{
			res = Integer.parseInt(Character.toString(s.charAt(0))) + Integer.parseInt(Character.toString(s.charAt(1)));
		}
		else if(length == 3)
		{
			if(Integer.parseInt(Character.toString(s.charAt(length - 1))) == 0) 
			{
				res = Integer.parseInt(Character.toString(s.charAt(0))) + 10;
			}
			else
			{
				res = Integer.parseInt(Character.toString(s.charAt(length - 1))) + 10;
			}
		}
		else
		{
			res = 20;
		}
		
		System.out.println(res);
	}
}
