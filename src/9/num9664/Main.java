package num9664;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int o = Integer.parseInt(br.readLine());
		
		n -= 1;
		int d = o / n;
		if(o == n * d)
			System.out.println((o + d - 1) + " " + (o + d));
		else
			System.out.println((o + d) + " " + (o + d));
	}
}
