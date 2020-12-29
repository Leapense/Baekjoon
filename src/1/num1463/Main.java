package num1463;

import java.util.Scanner;

public class Main {
	public static int n;
	public static int[] cnt = new int[10000001];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		if(n == 1)
			cnt[1] = 0;
		else
			solution(2, n);
		
		System.out.println(cnt[n]);
	}
	public static void solution(int i, int n)
	{
		if(i <= n)
		{
			if(i % 2 == 0 && i % 3 == 0)
				cnt[i] = Math.min(cnt[i / 2], cnt[i / 3]) + 1;
			else if(i % 2 == 0)
				cnt[i] = Math.min(cnt[i / 2], cnt[i - 1]) + 1;
			else if(i % 3 == 0)
				cnt[i] = Math.min(cnt[i / 3], cnt[i - 1]) + 1;
			else
				cnt[i] = cnt[i - 1] + 1;
			solution(i + 1, n);
		}
	}
}
