package num9461;

import java.util.Scanner;

public class Main {
	public static Long[] arr = new Long[101];
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		arr[0] = 0L;
		arr[1] = 1L;
		arr[2] = 1L;
		arr[3] = 1L;
		int test_case = sc.nextInt();
		
		while(test_case-- > 0)
		{
			int n = sc.nextInt();
			
			System.out.println(Padovan(n));
		}
	}
	public static long Padovan(int n)
	{
		if(arr[n] == null)
		{
			arr[n] = Padovan(n - 2) + Padovan(n - 3);
		}
		return arr[n];
	}
}
