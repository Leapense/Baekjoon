package num2953;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] result = new int[5];
		int sum = 0;
		for(int i = 0; i < 5; i++)
		{
			sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 4; j++)
			{
				sum += Integer.parseInt(st.nextToken());
			}
			result[i] = sum;
		}
		
		int max = Integer.MIN_VALUE;
		int maxind = 0;
		for(int i = 0; i < 5; i++)
		{
			if(max < result[i])
			{
				max = result[i];
				maxind = i + 1;
			}
		}
		System.out.println(maxind + " " + max);
	}
}
