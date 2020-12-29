package num16648;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int t = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		if(p >= 20)
		{
			double s = (double)(100 - p) / (double)t;
			
			System.out.println((p - 20) / s + 20 / (s / 2));
		}
		else
		{
			double s = (double)(80 + (20 - p) * 2) / t;
			System.out.println((p / (s / 2)));
		}
	}
}
