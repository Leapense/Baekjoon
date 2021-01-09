package num4504;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(true) {
			int a = Integer.parseInt(br.readLine());
			if(a == 0)
			{
				break;
			}
			else
			{
				sb.append(solution(n, a)).append('\n');
			}
		}
		System.out.println(sb);
	}
	public static String solution(int n, int a)
	{
		String answer = "";
		
		if(a % n == 0) {
			answer = a + " is a multiple of " + n + ".";
		}
		else {
			answer = a + " is NOT a multiple of " + n + ".";
		}
		
		return answer;
	}
}
