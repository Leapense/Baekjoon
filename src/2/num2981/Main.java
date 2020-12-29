package num2981;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int gcdVal = arr[1] - arr[0];
		
		for(int i = 2; i < n; i++)
		{
			gcdVal = gcd(gcdVal, arr[i] - arr[i - 1]);
		}
		
		for(int i = 2; i <= gcdVal; i++)
		{
			if(gcdVal % i == 0)
			{
				System.out.println(i);
			}
		}
	}
	static int gcd(int a, int b)
	{
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}
