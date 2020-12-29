package num2525;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int h = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int after = Integer.parseInt(br.readLine());
		
		System.out.println(((h + ((m + after) / 60)) % 24) + " " + ((m + after) % 60));
		
	}
}
