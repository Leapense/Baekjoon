package num1874;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		Stack<Integer> stack = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		int num = 1;
		boolean isAble = true;
		for(int i = 0; i < n; i++)
		{
			arr[i] = sc.nextInt();
			
			if(isAble)
			{
				if(num <= arr[i])
				{
					while(num <= arr[i])
					{
						stack.push(num++);
						sb.append("+\n");
					}
				}
				if(stack.isEmpty()) isAble = false;
				else
				{
					while(stack.peek() >= arr[i])
					{
						stack.pop();
						sb.append("-\n");
						if(stack.isEmpty())
						{
							break;
						}
					}
				}
			}
		}
		if(isAble) {
			System.out.println(sb);
		} else {
			System.out.println("NO");
		}
	}
}
