package num15652;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static int n, m;
	public static int list[];
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new int[9];
		print_back_tracking(0);
	}
	
	private static void print_back_tracking(int cnt)
	{
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				System.out.print(list[i]);
				if(i != m - 1)
					System.out.print(' ');
			}
			System.out.println();
			return;
		}
		for(int i = 1; i <= n; i++)
		{
			list[cnt] = i;
			if(cnt == 0)
				print_back_tracking(cnt + 1);
			else if(cnt > 0 && list[cnt] >= list[cnt - 1])
				print_back_tracking(cnt + 1);
			
		}
	}
}
