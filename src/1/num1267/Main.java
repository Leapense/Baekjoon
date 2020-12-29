package num1267;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] time = new int[n];
		int y = 0;
		int m = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++)
		{
			time[i] = Integer.parseInt(st.nextToken());
			y += (time[i] / 30) * 10 + 10;
			m += (time[i] / 60) * 15 + 15;
		}
		if(y > m)
		{
			System.out.println("M " + m);
		}
		else if(y < m)
		{
			System.out.println("Y " + y);
		}
		else
		{
			System.out.println("Y M " + y);
		}
		br.close();
	}
}
