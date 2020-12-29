package num11720;

import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int sum = 0;
		for(int i = 0; i < n; i++)
		{
			sum += Integer.parseInt(s.substring(i, i + 1));
		}
		
		System.out.println(sum);
	}
}
