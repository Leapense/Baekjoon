package num1009;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long result = 1;
			for(int i = 0; i < b; i++)
			{
				result = result * a;
				result = result % 10;
			}
			if(result == 0) System.out.println("10");
			else
			{
				System.out.println(result);
			}
		}
	}
}
