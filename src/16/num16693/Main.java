package num16693;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int a = Integer.parseInt(st.nextToken());
		int p1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());
		
		if((double)(a / p1) < (double)(Math.PI * Math.pow(r, 2)) / (double) p2)
		{
			System.out.println("Whole pizza");
		}
		else
		{
			System.out.println("Slice of pizza");
		}
	}
}
