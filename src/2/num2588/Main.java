package num2588;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a, b, h, t, o;
		a = Integer.parseInt(br.readLine());
		b = Integer.parseInt(br.readLine());
		h = b / 100;
		t = (b % 100) / 10;
		o = (b % 100) % 10;
		
		System.out.println(a * o);
		System.out.println(a * t);
		System.out.println(a * h);
		System.out.println(a * o + 10 * (a * t) + 100 * (a * h));
	}
}
