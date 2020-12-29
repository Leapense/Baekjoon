package num1193;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		boolean findFraction = false;
		int up = 1, down = 1, h = 1, num = 1;
		
		for(int i = 0; i <= x; i++)
		{
			for(int j = 0; j < i; j++)
			{
				if(h % 2 == 0)
				{
					up = i - j;
					down = j + 1;
				} else {
					up = j + 1;
					down = i - j;
				}
				if(num == x)
				{
					findFraction = true;
					break;
				}
				num++;
			}
			if(findFraction)
			{
				break;
			}
			h++;
		}
		
		System.out.println(up+"/"+down);
	}
}
