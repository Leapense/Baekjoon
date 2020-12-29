package num1032;

import java.io.*;

public class Main {
	static String[] fileName;
	static String str = "";
	static boolean isSame = true;
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(input.readLine());
		
		fileName = new String[n];
		for(int i = 0; i < n; i++)
		{
			fileName[i] = input.readLine().trim();
		}
		
		for(int i = 0; i < fileName[0].length(); i++)
		{
			for(int j = 1; j < n; j++)
			{
				if(fileName[0].charAt(i) == fileName[j].charAt(i)) 
				{
					isSame = true;
				}
				else
				{
					isSame = false;
					break;
				}
			}
			if(isSame == false)
			{
				str += "?";
			}
			else
			{
				str += fileName[0].charAt(i);
			}
		}
		System.out.println(str);
		input.close();
	}
}
