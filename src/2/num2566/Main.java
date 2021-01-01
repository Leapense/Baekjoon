/*
* Execution Time: 76ms
*/
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] num_pan = new int[9][9];
		int max = 0;
		for(int i = 0; i < 9; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 9; j++)
			{
				num_pan[i][j] = Integer.parseInt(st.nextToken());
				if(max < num_pan[i][j])
				{
					max = num_pan[i][j];
				}
			}
		}
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(num_pan[i][j] == max)
				{
					System.out.println(max);
					System.out.println((i + 1) + " " + (j + 1));
					return;
				}
			}
		}
		
	}
}
