package num2914;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		System.out.println(Integer.parseInt(st.nextToken()) * (Integer.parseInt(st.nextToken()) - 1) + 1);
	}
}
