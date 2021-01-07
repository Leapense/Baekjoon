package num2997;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] input = new int[3];
		for(int i = 0; i < 3; i++)
		{
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		int res;
		int d1 = input[1] - input[0];
		int d2 = input[2] - input[1];
		if(d1 == d2) {
			res = input[2] + d1;
		}
		else if(d1 > d2)
		{
			res = input[0] + d2;
		}
		else
		{
			res = input[1] + d1;
		}
		System.out.println(res);
	}
}
