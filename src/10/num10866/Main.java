package num10866;

import java.util.ArrayDeque;
import java.util.Deque;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Deque<Integer> deq = new ArrayDeque<Integer>();
		for(int i = 0; i < t; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String s = st.nextToken();
			if(s.equals("push_back"))
			{
				int n = Integer.parseInt(st.nextToken());
				deq.addFirst(n);
			}
			else if(s.equals("push_front"))
			{
				int n = Integer.parseInt(st.nextToken());
				deq.addLast(n);
			}
			else if(s.equals("front"))
			{
				sb.append(deq.isEmpty() ? -1 : deq.peekLast()).append('\n');
			}
			else if(s.equals("back"))
			{
				sb.append(deq.isEmpty() ? -1 : deq.peekFirst()).append('\n');
			}
			else if(s.equals("size"))
			{
				sb.append(deq.size()).append('\n');
			}
			else if(s.equals("empty"))
			{
				sb.append((deq.isEmpty()) ? 1 : 0).append('\n');
			}
			else if(s.equals("pop_front"))
			{
				sb.append((deq.isEmpty()) ? -1 : deq.pollLast()).append('\n');
			}
			else if(s.equals("pop_back"))
			{
				sb.append((deq.isEmpty()) ? -1 : deq.pollFirst()).append('\n');
			}
		}
		System.out.println(sb);
	}
}
