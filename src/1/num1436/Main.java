package num1436;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int cnt = 0;
		int temp = 666;
		int result = 0;
		
		int T = 0, N;
		
		N = in.nextInt();
		
		while(cnt < N) {
			result = temp;
			while(result >= 100) {
				if(result % 10 == 6) {
					result = result / 10;
					if(result % 10 == 6) {
						result = result / 10;
						if(result % 10 == 6) {
							result = result / 10;
							cnt++;
							T = temp;
						}
					}
				}
				result = result / 10;
			}
			temp++;
		}
		
		System.out.println(T);
	}
}
