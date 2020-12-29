package num15048;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		BigInteger bignum = new BigInteger(sc.nextLine());
		System.out.println(bignum.mod(new BigInteger("3")));
	}
}
