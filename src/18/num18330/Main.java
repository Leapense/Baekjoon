package num18330;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		int x = Math.min(k + 60, n);
		
		System.out.println(x * 1500 + (n - x) * 3000);
	}
}
