package num1065;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(getHan(n));
	}
	private static int getHan(int n) {
		int count = 0;
		for(int i = 1; i <= n; i++)
		{
			if(i >= 1 && i <= 99) {
				count++;
			}
			else {
				int a = i / 100;
				int b = (int)(i % 100) / 10;
				int c = (int)(i % 100) % 10;
				
				if(b - a == c - b)
				{
					count += 1;
				}
			} 
		}
		return count;
	}
}
