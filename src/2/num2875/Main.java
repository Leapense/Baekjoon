package num2875;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		int n, m, k;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int count = 0;
		while((n + m) >= k && n >= 0 && m >= 0)
		{
			n -= 2;
			m -= 1;
			count += 1;
		}
		System.out.println(count - 1);
	}
}
