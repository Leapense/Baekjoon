package num2798;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), max = sc.nextInt();
		int sum = 0;
		int res = 0;
		int[] arr = new int[n];
		for(int i = 0; i < n; i++)
		{
			arr[i] = sc.nextInt();
		}
		
		for(int i = 0; i < n-2; i++)
		{
			for(int j = i + 1; j < n-1; j++)
			{
				for(int k = i + 2; k < n; k++)
				{
					res = arr[i] + arr[j] + arr[k];
					if(sum < res && res <= max) {
						if(i != j && j != k) {
							sum = res;
						}
					}
				}
			}
		}
		System.out.println(sum);
	}
}
