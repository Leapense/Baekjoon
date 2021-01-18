package num6131;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int count = 0;
		for(int i = 1; i <= 500; i++)
		{
			for(int j = 1; j <= 500; j++)
			{
				if(i * i - j * j == n)
				{
					count += 1;
					//노가다로 할 수 밖에 없음.
				}
			}
		}
		System.out.println(count);
	}
}

