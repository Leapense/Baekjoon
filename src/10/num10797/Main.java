package num10797;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int count = 0;
		for(int i = 0; i < 5; i++)
		{
			if(Integer.parseInt(st.nextToken()) == num)
			{
				count++;
			}
		}
		
		System.out.println(count);
	}
}
