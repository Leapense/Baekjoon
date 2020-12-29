package num17496;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int cnt = 1;
		int day = 0;
		while(cnt <= n)
		{
			cnt += t;
			day++;
		}
		day -= 1;
		int sum = day * c * p;
		System.out.println(sum);
	}
}
