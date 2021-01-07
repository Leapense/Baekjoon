package num3034;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		while(n-- > 0)
		{
			int len = Integer.parseInt(br.readLine());
			if(len <= w || len <= h || len <= Math.sqrt(w * w + h * h))
			{
				System.out.println("DA");
			}
			else
			{
				System.out.println("NE");
			}
		}
	}
}
