import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			sc.nextLine();
			
			int n = sc.nextInt();

			BigInteger total = BigInteger.ZERO;
			for(int j = 0; j < n; j++)
			{
				total = total.add(sc.nextBigInteger());
			}
			if(total.mod(BigInteger.valueOf(n)).equals(BigInteger.ZERO) == false)
			{
				System.out.println("NO");
			}
			else
			{
				System.out.println("YES");
			}
		}
	}
}
