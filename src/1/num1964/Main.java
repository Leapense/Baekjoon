package num1964;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		
		System.out.println((n * (n - 1) * 3 / 2 + n * 4 + 1) % 45678);
		br.close();
	}
}
