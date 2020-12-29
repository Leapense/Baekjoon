package num1780;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static int[][] paper_towel;	// setting paper towel status
	private static int n, m;	// setting n is for paper towel width, height. m is for printing value.
	private static LinkedList<Integer> result = new LinkedList<>();
	private static int minus_one_cnt = 0, zero_cnt = 0, one_cnt = 0;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		paper_towel = new int[n][n];	// width * height of paper_towel
		
		for(int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++)
			{
				paper_towel[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		divide(0, 0, n);
		for(int val : result)
		{
			if(val == -1) minus_one_cnt += 1;
			else if(val == 0) zero_cnt += 1;
			else if(val == 1) one_cnt += 1;
		}
		System.out.println(minus_one_cnt);
		System.out.println(zero_cnt);
		System.out.println(one_cnt);
	}
	private static boolean check(int row, int col, int n)
	{
		int std = paper_towel[row][col];
		for(int i = row; i < row + n; i++)
		{
			for(int j = col; j < col + n; j++)
			{
				if(std != paper_towel[i][j]) {
					return false;
				}
			}
		}
		m = std;
		return true;
	}
	private static void divide(int row, int col, int n)
	{
		if(check(row, col, n)) {
			result.add(m);
		} else {
			int s = n / 3;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					divide(row + s * i, col + s * j, s);
				}
			}
		}
	}
}
