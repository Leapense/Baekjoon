package num6764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		int d = Integer.parseInt(br.readLine());
		
		if(a < b && b < c && c < d)
			System.out.println("Fish Rising");
		else if(a > b && b > c && c > d)
			System.out.println("Fish Diving");
		else if(a == b && b == c && c == d)
			System.out.println("Fish At Constant Depth");
		else
			System.out.println("No Fish");
	}
}
