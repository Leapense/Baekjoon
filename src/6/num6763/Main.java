package num6763;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int f, speed;
		f = Integer.parseInt(br.readLine());
		speed = Integer.parseInt(br.readLine());
		
		int result = speed - f;
		
		if(result <= 0)
		{
			System.out.println("Congratulations, you are within the speed limit!");
		}
		else
		{
			if(1 <= result && result <= 20)
			{
				System.out.println("You are speeding and your fine is $100.");
			}
			else if(21 <= result && result <= 30)
			{
				System.out.println("You are speeding and your fine is $270.");
			}
			else if(31 <= result)
			{
				System.out.println("You are speeding and your fine is $500.");
			}
		}
	}
}
