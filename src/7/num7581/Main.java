package num7581;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		/**
		 * @param l - length
		 * @param w - width
		 * @param h - height
		 * @param v - volume
		 */
		while(true)
		{
			String[] input = br.readLine().split(" ");
			int l = Integer.parseInt(input[0]);
			int w = Integer.parseInt(input[1]);
			int h = Integer.parseInt(input[2]);
			int v = Integer.parseInt(input[3]);
			
			if(l == 0 && w == 0 && h == 0 && v == 0)
			{
				break;
			}
			else
			{
				if(l == 0)
				{
					l = (int)(v / (w * h));
				}
				else if(w == 0)
				{
					w = (int)(v / (l * h));
				}
				else if(h == 0)
				{
					h = (int)(v / (w * l));
				}
				else if(v == 0)
				{
					v = w * h * l;
				}
				sb.append(l + " " + w + " " + h + " " + v).append('\n');
			}
		}
		System.out.println(sb);
	}
}
