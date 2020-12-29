package num17903;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		int m, n;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		if(m < 8)
		{
			System.out.println("unsatisfactory");
		}
		else
		{
			System.out.println("satisfactory");
		}
	}
}
