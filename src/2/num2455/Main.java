package num2455;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] count = new int[4];
		StringTokenizer st = null;
		int max = 0;
		for(int i = 0; i < 4; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			int out = Integer.parseInt(st.nextToken());
			int in= Integer.parseInt(st.nextToken());
			if(i > 0)
			{
				count[i] = count[i - 1];
			}
			count[i] += (in - out);
			if(max < count[i])
			{
				max = count[i];
			}
		}
		System.out.println(max);
	}
}
