package num1330;

import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// a, b의 값 저장
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		if(a > b) System.out.println(">");
		else if(a < b) System.out.println("<");
		else System.out.println("==");
		
		br.close();
		//이건 해도 되고, 안해도 되고.
	}
}
