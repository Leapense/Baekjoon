package num2164;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int i = 1;
		Queue<Integer> que = new LinkedList<Integer>();
		for(i = 1; i <= n; i++)
		{
			que.add(i);
		}
		while(que.size() != 1)
		{
			que.poll();
			que.offer(que.peek());
			que.poll();
		}
		System.out.println(que.element());
	}
}
