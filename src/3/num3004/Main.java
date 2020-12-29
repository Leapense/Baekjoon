package num3004;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int row = n/2;
		int col = n - row;
		
		System.out.println((row + 1) * (col + 1));
		
	}
}
