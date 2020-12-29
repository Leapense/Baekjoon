package num1992;

import java.io.*;

public class Main {
	private static int[][] pixel;
	private static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		pixel = new int[n][n];
		int[] num = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			String num_1 = br.readLine();
			for(int j = 0; j < n; j++) {
				num[j] = num_1.charAt(j);
				pixel[i][j] = (int)num[j] - 48;
			}
		}
		divide(0, 0, n);
	}
	
	private static boolean check(int row, int col, int n)
	{
		int std = pixel[row][col];
		for(int i = row; i < row + n; i++)
		{
			for(int j = col; j < col + n; j++)
			{
				if(std != pixel[i][j]) {
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
			System.out.print(m);
		} else {
			System.out.print("(");
			int s = n / 2;
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					divide(row + s * i, col + s * j, s);
				}
			}
			System.out.print(")");
		}
	}
}
