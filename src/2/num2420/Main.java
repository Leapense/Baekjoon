package num2420;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long n = Long.parseLong(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		
		System.out.println(Math.abs(n - m));
	}
}
