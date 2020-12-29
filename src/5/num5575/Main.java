package num5575;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int h1, m1, s1, h2, m2, s2;
		int h, m, s;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			h1 = Integer.parseInt(st.nextToken());
			m1 = Integer.parseInt(st.nextToken());
			s1 = Integer.parseInt(st.nextToken());
			h2 = Integer.parseInt(st.nextToken());
			m2 = Integer.parseInt(st.nextToken());
			s2 = Integer.parseInt(st.nextToken());
			
			s = (s2 - s1) % 60;
			m = (m2 - m1) % 60;
			h = (h2 - h1) % 24;
			if(s < 0)
			{
				s = 60 + s;
				m -= 1;
			}
			if(m < 0)
			{
				m = 60 + m;
				h -= 1;
			}
			sb.append(h + " " + m + " " + s).append('\n');
		}
		System.out.println(sb);
	}
} 

