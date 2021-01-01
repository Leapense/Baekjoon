package num2765;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int trip_number = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			double r = Double.parseDouble(st.nextToken());
			int rotcnt = Integer.parseInt(st.nextToken());
			int hour = Integer.parseInt(st.nextToken());
			if(rotcnt == 0)
			{
				break;
			}
			else
			{
				double dis = r * 3.1415927 * (double)rotcnt / 63360.0;
				double mph = dis * 3600.0 / (double)hour;
				System.out.printf("Trip #%d: %.2f %.2f\n", trip_number, dis, mph);
				trip_number += 1;
			}
		}
	}
}
