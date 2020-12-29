package num7568;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] height = new int[n];
		int[] weight = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			height[i] = sc.nextInt();
			weight[i] = sc.nextInt();
		}
		
		int grade = 1;
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(i == j) continue;
				if(height[i] < height[j] && weight[i] < weight[j])
				{
					grade += 1;
				}
			}
			System.out.println(grade);
			grade = 1;
		}
	}
}
