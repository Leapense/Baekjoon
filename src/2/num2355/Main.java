package num2355;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long temp;
		if(a > b)
		{
			temp = b;
			b = a;
			a = temp;
		}
		
		System.out.println((a + b) * (b - a + 1) / 2);
		br.close();
	}
}
