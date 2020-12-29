package num14935;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		for(int i = 0; i < s.length(); i++)
		{
			if(s.length() == 1)
				break;
			s = Integer.toString((s.charAt(0) - '0') * s.length());
		}
		if(s.length() == 1)
			System.out.println("FA");
		else
			System.out.println("NFA");
		br.close();
	}
}
