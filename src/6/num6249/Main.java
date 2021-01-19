package num6249;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		/**
		 * @param n	특정 날의 달러 가격이다.
		 * @param p	특정 날의 현재 달러 가격이다.
		 * @param h	특정 날의 사상 최고 달러 가격이다.
		 * @param input	테스트 케이스 입력하는 것이다.
		 * 
		 */
		int n = Integer.parseInt(input[0]);
		int p = Integer.parseInt(input[1]);
		int h = Integer.parseInt(input[2]);
		
		for(int i = 0; i < n; i++)
		{
			/**
			 * @param x	현재 달러 가격 입력
			 */
			int x = Integer.parseInt(br.readLine());
			if(x < p)
			{
				System.out.println("NTV: Dollar dropped by " + (p - x) + " Oshloobs");
				p = x;
			}
			else if(x > p && x > h)
			{
				System.out.println("BBTV: Dollar reached " + x + " Oshloobs, A record!");
				h = x;
				p = x;
			}
			else
			{
				p = x;
			}
		}
	}
}
