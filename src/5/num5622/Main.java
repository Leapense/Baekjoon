package num5622;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		int second, total = 0;
		
		for(int i = 0; i < s.length(); i++)
		{
			second = 2;
			char c1 = s.charAt(i);
			if(c1 >= 'A' && c1 <= 'C')
			{
				second += 1;
			}
			else if(c1 >= 'D' && c1 <= 'F')
			{
				second += 2;
			}
			else if(c1 >= 'G' && c1 <= 'I')
			{
				second += 3;
			}
			else if(c1 >= 'J' && c1 <= 'L')
			{
				second += 4;
			}
			else if(c1 >= 'M' && c1 <= 'O')
			{
				second += 5;
			}
			else if(c1 >='P' && c1 <= 'S')
			{
				second += 6;
			}
			else if(c1 >= 'T' && c1 <= 'V')
			{
				second += 7;
			}
			else if(c1 >= 'W' && c1 <= 'Z')
			{
				second += 8;
			}
			total += second;
			
		}
		System.out.println(total);
	}
}
