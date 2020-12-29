package num11053;

import java.util.Scanner;

public class Main {
	public static int[] arr, d;
	public static int len;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		len = sc.nextInt();
		int max = 0;
		arr = new int[len];
		d = new int[len];
		for(int i = 0; i < len; i++)
		{
			arr[i] = sc.nextInt();
		}
	
		d[0] = 1;
		
		for(int i = 1; i < len; i++)
		{
			d[i] = 1;
			for(int j = 0; j < i; j++)
			{
				if(arr[j] < arr[i] && d[i] <= d[j]) {
					d[i] = d[j] + 1;
				}
				max = Math.max(max, d[i]);
			}
		}
        System.out.println(max);
	}
}