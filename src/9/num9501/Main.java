package num9501;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0)
		{
			int count = 0;
			String[] input1 = br.readLine().split(" ");
			int n = Integer.parseInt(input1[0]);
			int d = Integer.parseInt(input1[1]);
			while(n-- > 0)
			{
				String[] input2 = br.readLine().split(" ");
				double vi = Double.parseDouble(input2[0]);
				double fi = Double.parseDouble(input2[1]);
				double ci = Double.parseDouble(input2[2]);
				
				if(vi * (fi / ci) >= d)
				{
					count += 1;
				}
			}
			System.out.println(count);
		}
	}
}
