package num2720;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int quarter, dime, nickel, penny;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++)
		{
			
			int n = Integer.parseInt(br.readLine());
			quarter = n / 25;
			n %= 25;
			dime = n / 10;
			n %= 10;
			nickel = n / 5;
			n %= 5;
			penny = n;
			sb.append(quarter).append(" ").append(dime).append(" ").append(nickel).append(" ").append(penny).append('\n');
		}
		System.out.println(sb);
	}
}
