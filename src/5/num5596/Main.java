package num5596;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int info1, math1, scie1, eng1;
		info1 = Integer.parseInt(st.nextToken());
		math1 = Integer.parseInt(st.nextToken());
		scie1 = Integer.parseInt(st.nextToken());
		eng1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int info2, math2, scie2, eng2;
		info2 = Integer.parseInt(st.nextToken());
		math2 = Integer.parseInt(st.nextToken());
		scie2 = Integer.parseInt(st.nextToken());
		eng2 = Integer.parseInt(st.nextToken());
		
		int s = info1 + math1 + scie1 + eng1;
		int t = info2 + math2 + scie2 + eng2;
		
		if(s == t)
		{
			System.out.println(s);
		}
		else
		{
			System.out.println((s > t) ? s : t);
		}
	}
} 
