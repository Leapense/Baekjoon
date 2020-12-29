package num11504;

import java.util.Scanner;

public class Main {
	public static int[] numArr;
	public static Integer[] r_dp;
	public static Integer[] l_dp;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int len = sc.nextInt();
		numArr = new int[len];
		r_dp = new Integer[len];
		l_dp = new Integer[len];
		for(int i = 0; i < len; i++)
		{
			numArr[i] = sc.nextInt();
		}
		
		
		for(int i = 0; i < len; i++)
		{
			LIS(i);
			LDS(i);
		}
		
		int max = -1;
		
		for(int i = 0; i < len; i++)
		{
			max = Math.max(r_dp[i] + l_dp[i], max);
		}
		
		System.out.println(max - 1);
		
		
	}
	
	public static int LIS(int n)
	{
		if(r_dp[n]  == null)
		{
			r_dp[n] = 1;
			for(int i = n - 1; i >= 0; i--) {
				if(numArr[i] < numArr[n]) {
					r_dp[n] = Math.max(r_dp[n], LIS(i) + 1);
				}
			}
		}
		
		return r_dp[n];
	}
	
	public static int LDS(int n)
	{
		if(l_dp[n] == null)
		{
			l_dp[n] = 1;
			for(int i = n + 1; i < l_dp.length; i++) {
				if(numArr[i] < numArr[n])
				{
					l_dp[n] = Math.max(l_dp[n], LDS(i) + 1);
				}
			}
		}
		return l_dp[n];
	}
}
