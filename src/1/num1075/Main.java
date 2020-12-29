package num1075;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int i, j, temp = 0;
		int n, f, len;
		
		n = sc.nextInt();
		f = sc.nextInt();
		
		char[] str = new char[11];
		str = Integer.toString(n).toCharArray();
		len = str.length;
		
		for(i = 0; i < 10; i++)
		{
			str[len - 2] = (char) (i + '0');
			for(j = 0; j < 10; j++)
			{
				str[len - 1] = (char)(j + '0');
			}
			if(temp % f == 0) {
				break;
			}
		}
		
		System.out.printf("%c%c\n", str[len - 2], str[len - 1]);
		
	}
}
