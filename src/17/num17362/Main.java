package num17362;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long num = Long.parseLong(br.readLine()) % 8;
		
		if(num == 1) System.out.println(1);
		else if(num == 2 || num == 0) System.out.println(2);
		else if(num == 3 || num == 7) System.out.println(3);
		else if(num == 4 || num == 6) System.out.println(4);
		else System.out.println(5);
	}
}
