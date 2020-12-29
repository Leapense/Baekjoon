package num15025;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		if(l == 0 && r == 0)
			System.out.println("Not a moose");
		else if(l == r)
			System.out.println("Even "+(l + r));
		else
			System.out.println("Odd "+(Math.max(l, r) * 2));
		
		br.close();
	}
}
