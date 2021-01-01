// Execution Time: 80ms
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		int n;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < 7; i++)
		{
			n = Integer.parseInt(br.readLine());
			if(n % 2 != 0)
			{
				sum += n;
				if(min > n)
				{
					min = n;
				}
			}
		}
		if(min == Integer.MAX_VALUE)
		{
			System.out.println(-1);
			return;
		}
		System.out.println(sum);
		System.out.println(min);
	}
}
