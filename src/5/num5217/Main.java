package num5217;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String result = "";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++)
		{
			List<String> pairs = new ArrayList<>();
			
			int n = Integer.parseInt(br.readLine());
			sb.append("Pairs for " + n + ": ");
			for(int j = 1; j < (int)(n / 2) + ((n & 1) != 0 ? 1 : 0); j++)
			{
				pairs.add("%d %d".formatted(j, n - j));
			}
			sb.append(String.join(", ", pairs));
			sb.append('\n');
			result = sb.toString();
		}
		System.out.println(result);
	}
}
