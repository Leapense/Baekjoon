package num1316;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = 0;
		for(int i = 0; i < n; i++)
		{
			String str = br.readLine();
			if(wordCheck(str))
			{
				count++;
			}
		}
		System.out.println(count);
	}
	private static boolean wordCheck(String str)
	{
		boolean[] check = new boolean[26];
		boolean group = true;
		char old = str.charAt(0);
		check[old - 97] = true;
		
		for(int i = 1; i < str.length(); i++)
		{
			char nw = str.charAt(i);
			if(!check[nw-97] && nw != old) {
				check[nw-97] = true;
				old = str.charAt(i);
			} else if(check[nw-97] && nw != old) {
				group = false;
				break;
			}
		}
		return group;
	}
}
