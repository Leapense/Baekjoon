package num13610;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		double count = 1.0;
		
		while(true)
		{
			if(count >= x * count / y + 1.0)
				break;
			count += 1.0;
		}
		
		System.out.println((int)count);
	}
}
