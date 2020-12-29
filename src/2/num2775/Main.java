package num2775;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int t, n, k;
		int[][] d = new int[15][15];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 1; i < 15; i++)
			d[0][i] = i;
		for(int i = 1; i < 15; i++)
		{
			for(int j = 1; j < 15; j++)
			{
				d[i][j] = d[i - 1][j] + d[i][j - 1];
			}
		}
		
		t = Integer.parseInt(br.readLine());
		while(t-- > 0)
		{
			k = Integer.parseInt(br.readLine());
			n = Integer.parseInt(br.readLine());
			System.out.println(d[k][n]);
		}
	}
}
