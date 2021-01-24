package num7770;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int x = 0;
		int h = 0;
		
		while(n >= x)
		{
			h++;
			x += h * h + (h - 1) * (h - 1);
		}
		if(n < x)
		{
			h--;
		}
		System.out.println(h);
	}
}
