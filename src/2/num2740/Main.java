package num2740;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	private static int[][] result;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] a = new int[n][m];
		for(int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++)
			{
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] b = new int[m][k];
		for(int i = 0; i < m; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < k; j++)
			{
				b[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = new int[a.length][b.length == 0 ? 0 : b[0].length];
		MMult(a, b);
		for(int i = 0; i < result.length; i++)
		{
			for(int j = 0; j < result[0].length; j++)
			{
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void MMult(int[][] m1, int[][] m2)
	{
		if((m1.length == 0 ? 0 : m1[0].length) == m2.length)
		{
			for(int i = 0; i < result.length; i++)
			{
				for(int j = 0; j < (result.length == 0 ? 0 : result[0].length); j++)
				{
					result[i][j] = 0;
					for(int k = 0; k < (m1.length == 0 ? 0 : m1[0].length); k++)
					{
						result[i][j] = result[i][j] + m1[i][k] * m2[k][j];
					}
				}
			}
		}
	}
}
