package num2511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] a = new int[10];
		for(int i = 0; i < 10; i++)
		{
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int[] b = new int[10];
		for(int i = 0; i < 10; i++)
		{
			b[i] = Integer.parseInt(st.nextToken());
		}
		int A_score = 0, B_score = 0;
		for(int i = 0; i < 10; i++)
		{
			if(a[i] < b[i])
			{
				B_score += 3;
			}
			else if(a[i] > b[i])
			{
				A_score += 3;
			}
			else
			{
				A_score += 1;
				B_score += 1;
			}
		}
		System.out.println(A_score + " " + B_score);
		if(A_score < B_score)
		{
			System.out.println("B");
		}
		else if(A_score > B_score)
		{
			System.out.println("A");
		}
		else
		{
			String winner = "D";
			for(int i = 9; i >= 0; i--)
			{
				if(a[i] < b[i])
				{
					winner = "B";
					break;
				}
				else if(a[i] > b[i])
				{
					winner = "A";
					break;
				}
			}
			System.out.println(winner);
		}
	}
}
