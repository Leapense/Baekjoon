package num1546;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		
		double[] score_sheet = new double[t];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < t; i++)
		{
			score_sheet[i] = Double.parseDouble(st.nextToken());
		}
		
		double max = score_sheet[0];
		
		for(int i = 0; i < t; i++)
		{
			if(max < score_sheet[i]) max = score_sheet[i];
		}
		
		double sum = 0.0;
		for(int i = 0; i < t; i++)
		{
			score_sheet[i] = (score_sheet[i] / max) * 100;
			sum += score_sheet[i];
		}
		System.out.println(sum / t);
		
	}
}
