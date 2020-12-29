package num1598;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int a, b;
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		int[] a_loc = new int[] {(int)((a - 1) / 4), (a - 1) % 4};
		int[] b_loc = new int[] {(int)((b - 1) / 4), (b - 1) % 4};
		System.out.println(Math.abs(b_loc[0] - a_loc[0]) + Math.abs(b_loc[1] - a_loc[1]));
		br.close();
	}
}
