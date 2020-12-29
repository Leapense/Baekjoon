package num19602;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		int s, m, l;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		l = Integer.parseInt(br.readLine());
		
		if(s + 2 * m + 3 * l >= 10)
			System.out.println("happy");
		else
			System.out.println("sad");
	}
}
