package num14264;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 이 문제는 정삼각형의 넓이를 구하라는 문제이다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long l = Long.parseLong(br.readLine());
		
		System.out.println((Math.sqrt(3) / 4.0) * Math.pow(l, 2));
	}
}
