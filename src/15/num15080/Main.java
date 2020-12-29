package num15080;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		String begin, end;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		begin = br.readLine().replace(" ", "");
		end = br.readLine().replace(" ", "");
		
		StringTokenizer st = new StringTokenizer(begin, ":");
		int begin_time = (Integer.parseInt(st.nextToken()) * 60 * 60) + (Integer.parseInt(st.nextToken()) * 60) + Integer.parseInt(st.nextToken());
		st = new StringTokenizer(end, ":");
		int end_time = (Integer.parseInt(st.nextToken()) * 60 * 60) + (Integer.parseInt(st.nextToken()) * 60) + Integer.parseInt(st.nextToken());
		
		int result = end_time - begin_time;
		if(result < 0)
		{
			result = 86400 + result;
		}
		System.out.println(result);
	}
}
