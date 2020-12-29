package num6768;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int j = Integer.parseInt(br.readLine());
		
		System.out.println((j < 3 ? 0 : (int)((j - 1) * (j - 2) * (j - 3) / 6)));
	}
}
