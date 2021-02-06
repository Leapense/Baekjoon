package num9724;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//Step 1: How many test you want?
		int t = Integer.parseInt(br.readLine());
		
		//Step 2: i = 0 -> i = t - 1 loop
		for(int i = 0; i < t; i++)
		{
			//Step 3: input string value with split.
			String[] input = br.readLine().split(" ");
			long a = Long.parseLong(input[0]);
			long b = Long.parseLong(input[1]);
			int count = (int)Math.floor(Math.pow(b,  1.0 / 3.0) - Math.ceil(Math.pow(a, 1.0 / 3.0))) + 1;
			System.out.println("Case #" +(i + 1) + ": " + count);
		}
	}
}
