package num15651;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static int n, m;
	public static int list[];
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new int[9];
		print_back_tracking(0);
		System.out.println(sb);
	}
	
	private static void print_back_tracking(int cnt)
	{
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(list[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 1; i <= n; i++)
		{
			list[cnt] = i;
			
			print_back_tracking(cnt + 1);
		}
	}
}

