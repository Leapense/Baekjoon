package num1712;

import java.util.StringTokenizer;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int a, b, c;
		int diff;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		diff = c - b;
		if(diff <= 0) System.out.println("-1");
		else {
			System.out.println(a / diff + 1);
		}
	}
}
