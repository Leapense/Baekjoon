package num10871;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++)
		{
			int a = Integer.parseInt(st.nextToken());
			if(a < x)
			{
				sb.append(a + " ");
			}
		}
		System.out.println(sb);
	}
}
