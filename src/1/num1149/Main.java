package num1149;

import java.util.Scanner;

public class Main {
	public static int[][] home;
	public static int[][] cost;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int h_cnt = sc.nextInt();
		
		home = new int[h_cnt][3];
		cost = new int[h_cnt][3];
		
		for(int i = 0; i < h_cnt; i++)
		{
			cost[i][0] = sc.nextInt();
			cost[i][1] = sc.nextInt();
			cost[i][2] = sc.nextInt();
		}
		
		home[0][0] = cost[0][0];
		home[0][1] = cost[0][1];
		home[0][2] = cost[0][2];
		
		System.out.println(Math.min(Paint_cost(h_cnt - 1, 0), Math.min(Paint_cost(h_cnt - 1, 1), Paint_cost(h_cnt - 1, 2))));
	}
	
	public static int Paint_cost(int n, int color)
	{
		if(home[n][color] == 0) {
			if(color == 0) {
				home[n][0] = Math.min(Paint_cost(n - 1, 1), Paint_cost(n - 1, 2)) + cost[n][0];
			} else if(color == 1) {
				home[n][1] = Math.min(Paint_cost(n - 1, 0), Paint_cost(n - 1, 2)) + cost[n][1];
			} else {
				home[n][2] = Math.min(Paint_cost(n - 1, 0), Paint_cost(n - 1, 1)) + cost[n][2];
			}
		}
		
		return home[n][color];
	}
}
