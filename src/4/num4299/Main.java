package num4299;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double a, b;
		int sum, min;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sum = Integer.parseInt(st.nextToken());
		min = Integer.parseInt(st.nextToken());
		
		b = (double)(sum - min) / 2.0;
		a = (double)sum - (double)b;
		if(a - (int)a > 0 || sum < min)
		{
			System.out.println("-1");
		}
		else
		{
			System.out.println((int)a + " " + (int)b);
		}
		
	}
}