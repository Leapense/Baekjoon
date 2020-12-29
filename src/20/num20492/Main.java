package num20492;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		StringBuilder sb = new StringBuilder();
		long res1 = (long)(n * 0.78);
		long res2 = (long)((n - (n - (n * 0.8)) * 0.22));
		
		sb.append(res1).append(" ").append(res2).append('\n');
		System.out.println(sb);
	}
}
