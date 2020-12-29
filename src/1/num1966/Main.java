package num1966;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			ArrayList<Elements> list = new ArrayList<Elements>();
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++)
			{
				list.add(new Elements(j, Integer.parseInt(st.nextToken())));
			}
			int cnt = 1;
			base:
				while(!list.isEmpty()) {
					Elements e = list.get(0);
					for(int j = 1; j < list.size(); j++)
					{
						if(e.val < list.get(j).val) {
							list.remove(0);
							list.add(e);
							continue base;
						}
					}
					if(e.pos == m)
					{
						break;
					}
					list.remove(0);
					cnt++;
				}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
	static class Elements
	{
		int pos, val;
		public Elements(int pos, int val)
		{
			this.pos = pos;
			this.val = val;
		}
	}
}


