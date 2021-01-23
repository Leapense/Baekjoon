package num6794;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/**
		 * @param n - 1에서 10사이의 값을 입력하도록 한다.
		 * @param count - 경우의 수를 구하기 위한 출력 변수이다.
		 */
		int n = Integer.parseInt(br.readLine());
		int count = 0;
		
		if(n <= 5)
		{
			count += 1;
		}
		/**
		 * @param i - 입력값을 숫자 가르기를 하기 위한 수단 변수이다.
		 */
		int i = 0;
		
		while(true)
		{
			i++;
			if(i == n)
			{
				break;
			}
			if(i >= n - i && i <= 5 && n - i <= 5)
			{
				count += 1;
			}
		}
		
		System.out.println(count);
		
	}
}
