package num2565;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static int[][] wire;
	public static Integer[] dp;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		wire = new int[n][2];
		dp = new Integer[n];
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				wire[i][j] = sc.nextInt();
			}
		}
		
		Arrays.sort(wire, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int max = 0;
		
		for(int i = 0; i < n; i++)
		{
			max = Math.max(max, recur(i));
		}
		System.out.println(n - max);
	}
	
	public static int recur(int n)
	{
		if(dp[n] == null)
		{
			dp[n] = 1;
			for(int i = n + 1; i < dp.length; i++)
			{
				if(wire[n][1] < wire[i][1])
				{
					dp[n] = Math.max(dp[n], recur(i) + 1);
				}
			}
		}
		return dp[n];
	}
}
