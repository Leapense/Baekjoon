package num3046;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R1 = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		System.out.println((2 * S) - R1);
	}
}
