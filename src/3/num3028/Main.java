package num3028;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] cup = new int[] {1, 0, 0};
		String input = br.readLine();
		int temp;
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) == 'A')
			{
				temp = cup[0];
				cup[0] = cup[1];
				cup[1] = temp;
			}
			else if(input.charAt(i) == 'B')
			{
				temp = cup[1];
				cup[1] = cup[2];
				cup[2] = temp;
			}
			else if(input.charAt(i) == 'C')
			{
				temp = cup[0];
				cup[0] = cup[2];
				cup[2] = temp;
			}
		}
		for(int i = 0; i < 3; i++)
		{
			if(cup[i] == 1)
			{
				System.out.println(i + 1);
				break;
			}
		}
	}
}
