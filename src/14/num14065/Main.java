package num14065;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double x = Double.parseDouble(br.readLine());
		
		System.out.printf("%.6f\n", (100.0 / ((1.609344 / 3.785411784) * x)));
	}
}
