package num2475;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int checkNumber = 0;
		while(st.hasMoreTokens())
		{
			checkNumber += Math.pow(Integer.parseInt(st.nextToken()), 2);
		}
		System.out.println(checkNumber % 10);
	}
}
