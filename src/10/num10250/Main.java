package num10250;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int h, w, n, t;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if(n % h != 0) {
				if(n / h + 1 >= 10)
					System.out.printf("%d%d\n", n % h, n / h + 1);
				else
					System.out.printf("%d0%d\n", n % h, n / h + 1);
			} else {
				if(n / h >= 10)
					System.out.printf("%d%d\n", h, n / h);
				else
					System.out.printf("%d0%d\n", h, n / h);
			}
		}
		
	}
}
