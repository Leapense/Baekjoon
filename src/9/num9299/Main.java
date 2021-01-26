package num9299;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++)
		{
			String[] input = br.readLine().split(" ");
			ArrayList<Integer> coef = new ArrayList<>();
			ArrayList<Integer> coef2 = new ArrayList<>();
			
			for(int j = 0; j < input.length; j++)
			{
				coef.add(Integer.parseInt(input[j]));
			}
			int exponent = coef.get(0);
			coef.remove(input.length - 1);
			int CoefSize = coef.size();
			for(int j = 0; j < CoefSize; j++)
			{
				coef2.add(coef.get(j) * (exponent - j));
			}
			coef2.add(0, exponent - 1);
			
			sb.append("Case " + i + ": ");
			for(int result : coef2)
			{
				sb.append(result).append(" ");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
/*
 * 이건 아마도 파이썬에만 가능할 듯...
 */
