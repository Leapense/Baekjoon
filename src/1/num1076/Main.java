package num1076;

import java.util.Scanner;

public class Main {
	public enum Res {
		black, brown, red, orange, yellow, green, blue, violet, grey, white
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String[] s = new String[3];
		for(int i = 0; i < 3; i++)
		{
			s[i] = sc.nextLine();
		}
		
		System.out.println((long)((Res.valueOf(s[0]).ordinal() * 10 + Res.valueOf(s[1]).ordinal()) * Math.pow(10, Res.valueOf(s[2]).ordinal())));
	}
}
