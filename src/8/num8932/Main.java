package num8932;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0)
		{
			String[] input = br.readLine().split(" ");
			int p1 = Integer.parseInt(input[0]);
			int p2 = Integer.parseInt(input[1]);
			int p3 = Integer.parseInt(input[2]);
			int p4 = Integer.parseInt(input[3]);
			int p5 = Integer.parseInt(input[4]);
			int p6 = Integer.parseInt(input[5]);
			int p7 = Integer.parseInt(input[6]);
			
			int score = 0;
			score += (9.23076 * Math.pow((26.7 - p1), 1.835));
			score += (1.84523 * Math.pow((p2 - 75), 1.348));
			score += (56.0211 * Math.pow((p3 - 1.5), 1.05));
			score += (4.99087 * Math.pow((42.5 - p4), 1.81));
			score += (0.188807 * Math.pow((p5 - 210), 1.41));
			score += (15.9803 * Math.pow((p6 - 3.8), 1.04));
			score += (0.11193 * Math.pow((254 - p7), 1.88));
			
			System.out.println((int)score);
		}
	}
}
