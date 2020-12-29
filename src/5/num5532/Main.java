package num5532;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		int d = Integer.parseInt(br.readLine());
		
		int count = 0;
		for(int i = 0; i < L; i++)
		{
			if(a <= 0 && b <= 0)
			{
				break;
			}
			else
			{
				a -= c;
				b -= d;
				count++;
			}
		}
		System.out.println(L-count);
	}
}
