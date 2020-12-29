package num11021;
import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int result = a + b;
			
			sb.append("Case #"+(i + 1)+": "+result+"\n");
		}
		System.out.println(sb.toString());
	}
}
