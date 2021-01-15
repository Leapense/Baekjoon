package num5220;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t-- > 0)
		{
			String[] input = br.readLine().split(" ");
			int number = Integer.parseInt(input[0]);
			int check_bits = Integer.parseInt(input[1]);
			int real_bits = 0;
			int count = 0;
			String binary = Integer.toBinaryString(number);
			for(int i = 0; i < binary.length(); i++)
			{
				if(binary.charAt(i) == '1')
				{
					count++;
				}
			}
			if(count % 2 == 0) {
				real_bits = 0;
			}
			else
			{
				real_bits = 1;
			}
			
			if(real_bits == check_bits)
			{
				sb.append("Valid").append('\n');
			}
			else
			{
				sb.append("Corrupt").append('\n');
			}
		}
		System.out.println(sb);
	}
}
