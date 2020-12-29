package num10773;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Stack<Integer> s = new Stack<>();
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++)
		{
			int x = sc.nextInt();
			if(x != 0)
			{
				s.push(x);
			}
			else
			{
				s.pop();
			}
		}
		int sum = 0;
		for(int i = 0; i < s.size(); i++)
		{
			sum += s.get(i);
		}
		System.out.println(sum);
	}
}
