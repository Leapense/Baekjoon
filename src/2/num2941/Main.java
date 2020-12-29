package num2941;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public final static String[] CA = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int count = s.length();
		int CA_count = 0;
		for(int i = 0; i < CA.length; i++)
		{
			Pattern p = Pattern.compile(CA[i]);
			Matcher m = p.matcher(s);
			while(m.find())
			{
				CA_count++;
			}
		}
		System.out.println(count - CA_count);
	}
}
