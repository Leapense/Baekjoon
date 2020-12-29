package num16017;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] digits = new int[4];
		
		for(int i = 0; i < 4; i++)
		{
			digits[i] = Integer.parseInt(br.readLine());
		}
		
		if((digits[0] == 9 || digits[0] == 8) && (digits[1] == digits[2]) && (digits[3] == 9 || digits[3] == 8))
		{
			System.out.println("ignore");
		}	
		else
			System.out.println("answer");
	}
}
