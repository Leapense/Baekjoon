package num1629;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(power(a % c, b, c));
	}
	private static long power(int a, int b, int c)
	{
		if(b == 1) return a;
		long temp = power(a, b / 2, c) % c;
		
		if(b % 2 == 0) {
			return (temp * temp) % c;
		}
		else {
			return (((temp * temp) % c) * a) % c;
		}
	}
}
