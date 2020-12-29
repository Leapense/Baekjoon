package num6749;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Y = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		System.out.println(M + (M - Y));
	}
}
