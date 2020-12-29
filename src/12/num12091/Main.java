package num12091;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		String[] str = new String[] {"Vaporeon", "Jolteon", "Flareon"};
		System.out.println(str[++n%3]);
	}
}
