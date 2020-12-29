package num9498;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		//Scanner는 가장 기본적인 입력 함수이지만, 시간이 걸린다는 문제입니다.
		//가급적이면 BufferedReader 클래스를 이용하는 것이 더 낫습니다.
		int score = sc.nextInt();
		if(score >= 90 && score <= 100) System.out.println("A");
		else if(score >= 80 && score < 90) System.out.println("B");
		else if(score >= 70 && score < 80) System.out.println("C");
		else if(score >= 60 && score < 70) System.out.println("D");
		else System.out.println("F");
		
		sc.close();
	}
}
