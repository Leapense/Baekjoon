package num9375;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			HashMap<String, Integer> hm = new HashMap<>();
			int n = Integer.parseInt(br.readLine());
			
			while(n-- > 0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				
				st.nextToken();
				
				String kind = st.nextToken();
				
				if(hm.containsKey(kind)) {
					hm.put(kind, hm.get(kind) + 1);
				} else {
					hm.put(kind, 1);
				}
				
			}
			int result = 1;
			for(int val : hm.values())
			{
				result *= (val + 1);
			}
			sb.append(result - 1).append('\n');
		}
		System.out.println(sb);
	}
}
