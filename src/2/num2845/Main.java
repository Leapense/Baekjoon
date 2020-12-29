package num2845;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int result = Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 5; i++)
		{
			sb.append((Integer.parseInt(st.nextToken()) - result) + " ");
		}
		System.out.println(sb);
	}
}
