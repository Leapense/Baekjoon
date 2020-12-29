package num2292;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int first = 1, plus = 6, room = 1;
		
		if(n == 1)
			System.out.println("1");
		else {
			while(true) {
				first += plus;
				room += 1;
				if(n <= first)
				{
					System.out.println(room);
					break;
				}
				plus += 6;
			}
		}
	}
}
