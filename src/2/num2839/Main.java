package num2839;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n, cnt = 0;
		n = sc.nextInt();
		
		while(n > 0)
		{
			if(n % 5 != 0) {
				n -= 3;
				if(n < 0) {
					cnt = -1;
					break;
				}
				cnt++;
			}
			else if(n % 5 == 0) {
				cnt++;
				n -= 5;
			}
			else if(n % 5 != 0 && n % 3 != 0)
			{
				cnt = -1;
			}
		}
		System.out.println(cnt);
	}
}
