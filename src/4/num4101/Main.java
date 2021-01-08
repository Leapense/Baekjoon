package num4101;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == 0 && b == 0)
			{
				break;
			}
			else
			{
				sb.append(solution(a, b)).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static String solution(int a, int b) {
		String answer = "";
		if(a > b)
		{
			answer = "Yes";
		}
		else 
		{
			answer = "No";
		}
		
		return answer;
	}
}
