package num10707;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();	// X사의 1리터당 요금
		int b = sc.nextInt();   // Y사의 기본 요금
		int c = sc.nextInt();	// Y사의 사용량의 상한
		int d = sc.nextInt();	// Y사의 1리터당 추가 요금
		int p = sc.nextInt();	// JOI군의 집에서 사용하는 한 달간 수도의 양
		
		int x = a * p;
		int y;
		if(p > c)
		{
			y = b + ((p - c) * d);
		}
		else
		{
			y = b;
		}
		if(x < y)
		{
			System.out.println(x);
		}
		else
		{
			System.out.println(y);
		}
	}
}
