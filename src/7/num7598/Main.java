package num7598;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String[] info = br.readLine().split(" ");
			String name = info[0];
			int n = Integer.parseInt(info[1]);
			if(name.equals("#") && n == 0)
			{
				break;
			}
			while(true)
			{
				String[] check = br.readLine().split(" ");
				String action = check[0];
				int amount = Integer.parseInt(check[1]);
				if(action.equals("X"))
				{
					break;
				}
				else if(action.equals("B"))
				{
					if(n + amount <= 68)
					{
						n += amount;
					}
				}
				else if(action.equals("C"))
				{
					if(n - amount >= 0)
					{
						n -= amount;
					}
				}
			}
			System.out.println(name + " " + n);
		}
	}
}
