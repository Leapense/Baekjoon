package num4714;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			double x = Double.parseDouble(br.readLine());
			if(x == -1.0)
			{
				break;
			}
			else
			{
				//Objects weighing 100.00 on Earth will weigh 16.70 on the moon.
				System.out.printf("Objects weighing %.2f on Earth will weigh %.2f on the moon.\n", x, solution(x));
			}
		}
	}
	public static double solution(double input)
	{
		return input * 0.167;
	}
}
