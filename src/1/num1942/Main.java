package num1942;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++)
		{
			String s = br.readLine().replace(":", " ");
			StringTokenizer st = new StringTokenizer(s, " ");
			int h1 = Integer.parseInt(st.nextToken());
			int m1 = Integer.parseInt(st.nextToken());
			int s1 = Integer.parseInt(st.nextToken());
			int h2 = Integer.parseInt(st.nextToken());
			int m2 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			
			int r1 = h1 * 3600 + m1 * 60 + s1;
			int r2 = h2 * 3600 + m2 * 60 + s2;
			int count = 0;
			if(r1 > r2)
			{
				r2 += 3600 * 24;
			}
			
			for(int j = 0, end = r2 - r1 + 1;  j < end; j++)
			{
				if((h1 * 10000 + m1 * 100 + s1) % 3 == 0)
				{
					count++;
				}
				if(++s1 == 60) {
					s1 = 0;
					m1++;
				}
				if(m1 == 60)
				{
					m1 = 0;
					h1++;
				}
				if(h1 == 24)
				{
					h1 = 0;
				}
			}
			sb.append(count).append('\n');
		}
		System.out.println(sb);
	}
}
