package num2884;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken()) - 45;
		
		if(minute < 0)
		{
			hour -= 1;
			if(hour < 0)
			{
				hour = 23;
			}
			minute = 60 - Math.abs(minute);
		}
		System.out.println(hour + " " + minute);
	}
}
