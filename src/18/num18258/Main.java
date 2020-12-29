package num18258;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		Queue<Integer> que = new LinkedList<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int last = 0;
		for(int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String cmd_name = st.nextToken();
			if(cmd_name.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				last = num;
				que.offer(num);
			}
			else if(cmd_name.equals("front")) {
				sb.append(que.isEmpty() ? "-1" : que.peek()).append("\n");
			}
			else if(cmd_name.equals("back")) {
				sb.append(que.isEmpty() ? "-1" : Integer.toString(last)).append("\n");
			}
			else if(cmd_name.equals("size")) {
				sb.append(que.size()).append("\n");
			}
			else if(cmd_name.equals("empty")) {
				sb.append(que.isEmpty() ? 1 : 0).append("\n");
			}
			else if(cmd_name.equals("pop")) {
				sb.append(que.isEmpty()? -1 : que.poll()).append("\n");
			}
		}
		System.out.println(sb);
	}
}
