package num9517;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		int time = 0;
		
		for(int i = 0; i < n; i++)
		{
			String[] input = br.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			String z = input[1];
			time += t;
			
			if(time >= 210)
			{
				System.out.println(k);
				break;
			}
			
			if(z.equals("T")) {
				k++;
				
				if(k > 8)
				{
					k = 1;
				}
			}
		}
	}
}
