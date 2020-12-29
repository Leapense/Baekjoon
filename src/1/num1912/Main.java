package num1912;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		// case 1: brute force 알고리즘 이용 - 안됨...
		// case 2: dP 알고리즘 이용
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			arr[i] = sc.nextInt();
		}
		int max = Integer.MIN_VALUE;
		
		System.out.println(dP(arr, n, max));
	}
	public static int dP(int[] arr, int size, int maxNum)
	{
		int partialSum = 0;
		
		for(int i = 0; i < size; i++) {
			partialSum = Math.max(0, partialSum) + arr[i];
			maxNum = Math.max(partialSum, maxNum);
		}
		
		return maxNum;
	}
}
