package num1152;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int count = 0;
		while(st.hasMoreTokens())
		{
			if(st.nextToken() != null)
			{
				count += 1;
			}
		}
		System.out.println(count);
		
	}
}
