package num6139;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] start_input = br.readLine().split(" ");
		double n = Double.parseDouble(start_input[0]);
		double k = Double.parseDouble(start_input[1]);
		
		while(k-- > 0.0)
		{
			String[] second_input = br.readLine().split(" ");
			double s = Double.parseDouble(second_input[0]);
			double t = Double.parseDouble(second_input[1]);
			double r = Double.parseDouble(second_input[2]);
			
			double count = 0;
			double time = 0;
			
			while(true)
			{
				if(n - count <= s * t)
				{
					time += Math.ceil((n - count) / s);
					break;
				}
				else
				{
					count += (s * t);
					time += (t + r);
				}
			}
			System.out.println((int)time);
		}
	}
}
