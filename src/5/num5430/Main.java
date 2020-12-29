package num5430;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		int k;
		StringTokenizer st = null;
		while(t-- > 0)
		{
			Deque<String> list = new LinkedList<>();
			String cmdd = br.readLine();
			char[] cmd = cmdd.toCharArray();
			k = Integer.parseInt(br.readLine());
			String input = br.readLine();
			input = input.substring(1, input.length() - 1);			
			
			if(k != 0)
			{
				st = new StringTokenizer(input, ",");
			}
			else
			{
				if(cmdd.contains("D"))
					sb.append("error").append('\n');
				else 
					sb.append("[]").append('\n');
				continue;
			}
			for(int i = 0; i < k; i++)
			{
				list.add(st.nextToken());
			}
			
			boolean r = true;
			boolean errorFlag = false;
			
			for(int i = 0; i < cmd.length; i++)
			{
				if(cmd[i] == 'R')
					r = !r;
				else {
					if(list.isEmpty()) {
						errorFlag = true;
						break;
					}
					
					if(r)
						list.pollFirst();
					else
						list.pollLast();
				}
			}
			if(!errorFlag) {
				sb.append("[");
				if(r) {
					while(list.size() > 1)
					{
						sb.append(list.pollFirst() + ",");
					}
				}
				else
				{
					while(list.size() > 1)
					{
						sb.append(list.pollLast() + ",");
					}
				}
				if(list.size() != 0)
					sb.append(list.getFirst());
				sb.append("]").append('\n');
			} else {
				sb.append("error").append('\n');
			}
		}
		System.out.println(sb);
	}
}
