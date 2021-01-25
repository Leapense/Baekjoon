package num9085;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0)
		{
			int n = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			int sum = 0;
			for(int i = 0; i < n; i++)
			{
				sum += Integer.parseInt(input[i]);
			}
			
			System.out.println(sum);
		}
	}
}
