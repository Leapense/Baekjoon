package num10828;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> s = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String cmd_name = st.nextToken();
			if(cmd_name.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				s.push(num);
			}
			else if(cmd_name.equals("pop")) {
				sb.append(s.isEmpty()? -1 : s.pop()).append("\n");
			}
			else if(cmd_name.equals("size")) {
				sb.append(s.size()).append("\n");
			}
			else if(cmd_name.equals("empty")) {
				sb.append(s.isEmpty() ? 1 : 0).append("\n");
			}
			else if(cmd_name.equals("top")) {
				sb.append(s.isEmpty() ? -1 : s.peek()).append("\n");
			}
		}
		System.out.println(sb);
	}
}
