package num8723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] sides = new int[3];
		for(int i = 0; i < 3; i++)
		{
			sides[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sides);
		
		if(sides[0] == sides[1] && sides[1] == sides[2])
		{
			System.out.println("2");
		}
		else if((int)(Math.pow(sides[0], 2)) + (int)(Math.pow(sides[1], 2)) == (int)(Math.pow(sides[2], 2)))
		{
			System.out.println("1");
		}
		else
		{
			System.out.println("0");
		}
	}
}
