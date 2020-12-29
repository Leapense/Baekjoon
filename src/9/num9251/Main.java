package num9251;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		String a = sc.nextLine();
		String b = sc.nextLine();
		
		int[][] LCS = new int[a.length() + 1][b.length() + 1];
		
		for(int i = 1; i <= a.length(); i++)
		{
			for(int j = 1; j <= b.length(); j++)
			{
				if(a.charAt(i - 1) == b.charAt(j - 1))
				{
					LCS[i][j] = LCS[i - 1][j - 1] + 1;
				}
				else
				{
					LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
				}
			}
		}
		
		System.out.println(LCS[LCS.length - 1][LCS[0].length - 1]);
	}
}
