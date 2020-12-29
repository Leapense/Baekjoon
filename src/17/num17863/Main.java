package num17863;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String number = br.readLine();
		
		if(number.substring(0, 3).equals("555"))
		{
			System.out.println("YES");
		}
		else
		{
			System.out.println("NO");
		}
	}
}
