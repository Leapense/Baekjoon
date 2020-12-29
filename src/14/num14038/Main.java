package num14038;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int win_count = 0;
		String s;
		for(int i = 0; i < 6; i++)
		{
			s = br.readLine();
			if(s.equals("W"))
			{
				win_count += 1;
			}
		}
		
		if(win_count == 5 || win_count == 6) System.out.println("1");
		else if(win_count == 3 || win_count == 4) System.out.println("2");
		else if(win_count == 1 || win_count == 2) System.out.println("3");
		else System.out.println("-1");
	}
}
