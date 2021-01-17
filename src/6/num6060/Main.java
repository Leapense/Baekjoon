package num6060;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		int[] x = new int[len + 1];
		int[] y = new int[len + 1];
		for(int i = 0; i < len - 1; i++)
		{
			String[] input = br.readLine().split(" ");
			int s = Integer.parseInt(input[0]);
			int d = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			x[s] = d;
			y[s] = c;
		}
		
		int direction = 0, a = 1;
		for(int i = 0; i < len - 1; i++)
		{
			if(y[a] == 1)
			{
				direction = 1 - direction;
			}
			a = x[a];
		}
		System.out.println(direction);
	}
}

