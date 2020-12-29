package num2530;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int h = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int after = Integer.parseInt(br.readLine());
		
		int after_h = (h + ((m + ((s + after) / 60)) / 60)) % 24;
		int after_m = (m + ((s + after) / 60)) % 60;
		int after_s = (s + after) % 60;
		
		System.out.println(after_h + " " + after_m + " " + after_s);
		
	}
}
