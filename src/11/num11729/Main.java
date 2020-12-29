package num11729;

import java.io.*;

public class Main {
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		sb.append((int)(Math.pow(2, n) - 1)).append('\n');
		hanoi(n, 1, 2, 3);
		System.out.println(sb);
	}
	private static void hanoi(int n, int start, int via, int to)
	{
		if(n == 1) {
			sb.append(start + " " + to + "\n");
			return;
		}
		else
		{
			hanoi(n - 1, start, to, via);
			sb.append(start + " " + to + "\n");
			hanoi(n - 1, via, start, to);
		}
	}
}
