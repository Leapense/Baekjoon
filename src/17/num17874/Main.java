package num17874;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int new_height = Math.max(h, n - h);
		int new_width = Math.max(v, n - v);
		
		System.out.println(new_height * new_width * 4);
	}
}
