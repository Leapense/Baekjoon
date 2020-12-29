package num10162;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		int fivemincnt = 0, onemincnt = 0, tenseccnt = 0;
		if(t % 10 != 0)
		{
			System.out.println("-1");
			return;
		}
		while(t >= 10)
		{
			if(t >= 300)
			{
				fivemincnt += 1;
				t -= 300;
			}
			else if(t >= 60)
			{
				onemincnt += 1;
				t -= 60;
			}
			else if(t >= 10)
			{
				tenseccnt += 1;
				t -= 10;
			}
		}
		System.out.println(fivemincnt + " " + onemincnt + " " + tenseccnt);
	}
}
