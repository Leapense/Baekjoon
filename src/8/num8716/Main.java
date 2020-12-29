package num8716;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long x1, y1, x2, y2;
		long x3, y3, x4, y4;
		
		x1 = Long.parseLong(st.nextToken());
		y1 = Long.parseLong(st.nextToken());
		x2 = Long.parseLong(st.nextToken());
		y2 = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		x3 = Long.parseLong(st.nextToken());
		y3 = Long.parseLong(st.nextToken());
		x4 = Long.parseLong(st.nextToken());
		y4 = Long.parseLong(st.nextToken());
		
		if(x2 < x3 || x1 > x4 || y2 > y3 || y1 < y4)
			System.out.println("0");
		else
			System.out.println(Math.abs(Math.min(x2, x4) - Math.max(x1, x3)) * Math.abs(Math.min(y1, y3) - Math.max(y2, y4)));
	}
}
