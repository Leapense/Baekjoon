package num10178;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		while(n-- > 0)
		{
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			sb.append("You get " + (int)(a / b) + " piece(s) and your dad gets " + (a % b) + " piece(s).").append('\n');
		}
		
		System.out.println(sb);
	}
}
