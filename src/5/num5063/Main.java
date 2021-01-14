package num5063;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test_number = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < test_number; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(r < e - c)
			{
				sb.append("advertise").append('\n');
			}
			else if(r == e - c)
			{
				sb.append("does not matter").append('\n');
			}
			else if(r > e - c)
			{
				sb.append("do not advertise").append('\n');
			}
		}
		System.out.println(sb);
	
	}
}
