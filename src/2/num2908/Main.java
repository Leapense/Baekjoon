package num2908;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		StringTokenizer st = new StringTokenizer(sb.reverse().toString(), " ");
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		if(a != b && a % 10 != 0 && b % 10 != 0)
		{
			if(a > b) System.out.println(a);
			else if(a < b) System.out.println(b);
		}
	}
}
