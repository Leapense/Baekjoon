package num1703;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int res = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			if(a == 0)
			{
				break;
			}
			int[] tmp = new int[2 * a];
			
			for(int i = 0; i < (2 * a); i++)
			{
				tmp[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < a; i++)
			{
				res *= tmp[i * 2];
				res -= tmp[i * 2 + 1];
			}
			sb.append(res).append('\n');
			res = 1;
		}
		System.out.println(sb);
	}
}
