package num2476;
import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, a, b, c;
		n = Integer.parseInt(br.readLine());
		int[] money = new int[n];
		int max = 0;
		for(int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a == b && b == c)
			{
				money[i] = 10000 + a * 1000;
			}
			else if(a == b || b == c || c == a)
			{
				money[i] = 1000 + (a == b ? a : b == c ? b : c) * 100;
			}
			else
			{
				money[i] = Math.max(a,  Math.max(b, c)) * 100;
			}
			
			if(max < money[i])
			{
				max = money[i];
			}
		}
		System.out.println(max);
	}
}
