package num1003;

import java.util.Scanner;

public class Main {
	static int zero;
	static int one;
	static int zero_plus_one;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < t; i++)
		{
			int n = sc.nextInt();
			fibonacci(n);
			sb.append(zero).append(' ').append(one).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void fibonacci(int n)
	{
		zero = 1;
		one = 0;
		zero_plus_one = 1;
		
		for(int i = 0; i < n; i++)
		{
			zero = one;
			one = zero_plus_one;
			zero_plus_one = zero + one;
		}
	}
}
