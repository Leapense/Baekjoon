package num2506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int score = 0;
		int combo = 1;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++)
		{
			if(Integer.parseInt(st.nextToken()) == 1)
			{
				score += combo;
				combo += 1;
			}
			else
			{
				combo = 1;
			}
		}
		System.out.println(score);
	}
}
