package num17388;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		ArrayList<Integer> SKH = new ArrayList<>();
		int sum = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 0; i < 3; i++)
		{
			SKH.add(Integer.parseInt(st.nextToken()));
			sum += SKH.get(i);
		}
		if(sum >= 100)
		{
			System.out.println("OK");
		}
		else
		{
			int min = Collections.min(SKH);
			if(SKH.indexOf(min) == 0)
			{
				System.out.println("Soongsil");
			}
			else if(SKH.indexOf(min) == 1)
			{
				System.out.println("Korea");
			}
			else
			{
				System.out.println("Hanyang");
			}
		}
		
	}
}
