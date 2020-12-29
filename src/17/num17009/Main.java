package num17009;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int a1, a2, a3, b1, b2, b3;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		a1 = Integer.parseInt(br.readLine());
		a2 = Integer.parseInt(br.readLine());
		a3 = Integer.parseInt(br.readLine());
		b1 = Integer.parseInt(br.readLine());
		b2 = Integer.parseInt(br.readLine());
		b3 = Integer.parseInt(br.readLine());
		
		int a_sum = a1 * 3 + a2 * 2 + a3;
		int b_sum = b1 * 3 + b2 * 2 + b3;
		
		if(a_sum < b_sum)
			System.out.println('B');
		else if(a_sum > b_sum)
			System.out.println('A');
		else
			System.out.println('T');
	}
}
