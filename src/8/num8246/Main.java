package num8246;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 해석: 두 개의 의자는 겹칠 수 없다.
	// 해석: 그러면 테이블에 몇 개의 의자가 필요할까?
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println((int)((a / k) * (b / k) - Math.max(a / k - 2, 0) * Math.max(b / k - 2, 0)));
	}
}
