package num11866;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int i, idx = 1, cnt = 1, total = 0;
		
		ArrayList<Boolean> check = new ArrayList<Boolean>();
		for(i = 0; i < n; i++)
		{
			check.add(false);
		}
		while(total != n) {
			if(check.get(idx - 1)) {
				idx = idx == n ? 1 : idx + 1;
				continue;
			}
			if(cnt == k) {
				check.set(idx - 1, true);
				total++;
				sb.append(idx);
				if(total != n) sb.append(", ");
				cnt = 1;
			} else {
				cnt++;
			}
			idx = idx == n ? 1 : idx + 1;
		}
		sb.append(">");
		System.out.println(sb);
	}
}
