package num20233;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int x = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int y = Integer.parseInt(br.readLine());
		int t = Integer.parseInt(br.readLine());
		int res1, res2;
		if(t <= 30)
		{
			res1 = a;
		}
		else
		{
			res1 = a + (x * (t - 30)) * 21;
		}
		if(t <= 45)
		{
			res2 = b;
		}
		else
		{
			res2 = b + (y * (t - 45)) * 21;
		}
		
		System.out.println(res1 + " " + res2);
		br.close();
	}
}
