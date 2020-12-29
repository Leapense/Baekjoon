package num1427;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		long n = sc.nextLong();
		String temp = Long.toString(n);
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < temp.length(); i++)
		{
			list.add(temp.charAt(i) - '0');
		}
		
		Collections.sort(list);
		Collections.reverse(list);
		
		for(int values : list)
		{
			System.out.print(values);
		}
		
	}
}
