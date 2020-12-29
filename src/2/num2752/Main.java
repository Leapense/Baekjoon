package num2752;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] sesu = new int[3];
		for(int i = 0; i < 3; i++)
		{
			sesu[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sesu);
		for(int val : sesu)
		{
			System.out.print(val + " ");
		}
		
	}
}
