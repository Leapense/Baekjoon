package num8718;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int x, k, v;
		
		x = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if(k * 7 <= x)
		{
			v = k * 7000;
		}
		else if((double)(k * 3.5) <= (double)x)
		{
			v = k * 3500;
		}
		else if((double)(k * 1.75) <= (double)x)
		{
			v = k * 1750;
		}
		else
		{
			v = 0;
		}
		System.out.println(v);
	}
}
