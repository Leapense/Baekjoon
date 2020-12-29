package num1297;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int a = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		double result = (Math.pow(a, 2) / (Math.pow(h, 2) + Math.pow(w, 2)));
		result = Math.sqrt(result);
		
		System.out.println((int)(h * result) + " " + (int)(w * result));
	}
}
