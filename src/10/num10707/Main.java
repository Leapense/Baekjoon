package num10707;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();	// X���� 1���ʹ� ���
		int b = sc.nextInt();   // Y���� �⺻ ���
		int c = sc.nextInt();	// Y���� ��뷮�� ����
		int d = sc.nextInt();	// Y���� 1���ʹ� �߰� ���
		int p = sc.nextInt();	// JOI���� ������ ����ϴ� �� �ް� ������ ��
		
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