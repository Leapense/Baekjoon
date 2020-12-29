package num15474;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		int n, a, b, c, d;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		a = (int)(n / a + ((n % a == 0) ? 0 : 1)) * b;
		c = (int)(n / c + ((n % c == 0) ? 0 : 1)) * d;
		
		System.out.println(Math.min(a, c));
	}
}
