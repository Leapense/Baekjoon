package num1932;

import java.util.Scanner;

public class Main {
	public static int[][] arr;
	public static Integer[][] dp;
	public static int n;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		arr = new int[n][n];
		dp = new Integer[n][n];
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < i + 1; j++)
			{
				arr[i][j] = sc.nextInt();
			}
		}
		for(int i = 0; i < n; i++)
		{
			dp[n - 1][i] = arr[n - 1][i];
		}
		
		System.out.println(find(0, 0));
		
	}
	
	public static int find(int depth, int idx)
	{
		if(depth == n - 1) return dp[depth][idx];
		
		if(dp[depth][idx] == null)
		{
			dp[depth][idx] = Math.max(find(depth + 1, idx), find(depth + 1, idx + 1)) + arr[depth][idx];
		}
		return  dp[depth][idx];
	}
}
