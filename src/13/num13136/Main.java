package num13136;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long r = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());
		long n = Long.parseLong(st.nextToken());
		long ret = (long)((r / n + (r % n != 0 ? 1 : 0)) * (c / n + (c % n != 0 ? 1 : 0)));
		System.out.println(ret);
	}
}
