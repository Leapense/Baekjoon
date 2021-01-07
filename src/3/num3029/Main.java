package num3029;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), ":");
		int h1 = Integer.parseInt(st.nextToken());
		int m1 = Integer.parseInt(st.nextToken());
		int s1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), ":");
		int h2 = Integer.parseInt(st.nextToken());
		int m2 = Integer.parseInt(st.nextToken());
		int s2 = Integer.parseInt(st.nextToken());
		
		if(s1 > s2) {
			s2 += 60;
			m2 -= 1;
		}
		if(m1 > m2) {
			m2 += 60;
			h2 -= 1;
		}
		if(h1 > h2) {
			h2 += 24;
		}
		
		int s = s2 - s1;
		int m = m2 - m1;
		int h = h2 - h1;
		
		if(s == 0 && m == 0 && h == 0)
		{
			h = 24;
		}
		
		System.out.printf("%02d:%02d:%02d\n", h, m, s);
	}
}
