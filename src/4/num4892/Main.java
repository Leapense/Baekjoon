package num4892;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n_0, n_1, n_2, n_3, n_4;
		StringBuilder sb = new StringBuilder();
		int case_number = 1;
		while(true)
		{
			n_0 = Integer.parseInt(br.readLine());
			if(n_0 == 0) break;
			
			sb.append(case_number + ". ");
			n_1 = n_0 * 3;
			if(n_1 % 2 == 0)
			{
				n_2 = n_1 / 2;
				sb.append("even ");
			}
			else
			{
				n_2 = (n_1 + 1) / 2;
				sb.append("odd ");
			}
			n_3 = 3 * n_2;
			n_4 = n_3 / 9;
			sb.append(n_4).append('\n');
			case_number += 1;
		}
		System.out.println(sb);
	}
}

