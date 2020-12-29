package num8958;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		int count = 0;
		int sum = 0;
		for(int i = 0; i < t; i++) {
			String s = br.readLine();
			
			for(int j = 0; j < s.length(); j++)
			{
				if(s.charAt(j) == 'O') count+= 1;
				else if(s.charAt(j) == 'X') count = 0;
				sum += count;
			}
			sb.append(sum + "\n");
			sum = 0;
			count = 0;
		}
		System.out.println(sb.toString());
	}
}
