package num11399;

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
		
		int prev = 0;
		int sum = 0;
		
		for(int i = 0; i < n; i++)
		{
			sum += prev + arr[i];
			prev += arr[i];
		}
		System.out.println(sum);
	}
}
