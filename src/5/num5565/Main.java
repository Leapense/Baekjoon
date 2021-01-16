package num5565;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int total_sum = Integer.parseInt(br.readLine());
		for(int i = 0; i < 9; i++)
		{
			int a = Integer.parseInt(br.readLine());
			total_sum -= a;
		}
		System.out.println(total_sum);
	}
}
