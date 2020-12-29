package num3003;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	private static int[] chess = new int[] {1, 1, 2, 2, 2, 8};
	private static int[] peaces = new int[chess.length];
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < chess.length; i++)
		{
			peaces[i] = Integer.parseInt(st.nextToken());
			sb.append((chess[i] - peaces[i]) + " ");
		}
		System.out.println(sb);
	}
}
