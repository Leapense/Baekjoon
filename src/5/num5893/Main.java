package num5893;
import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String binaryText = br.readLine();
		BigInteger n = new BigInteger(binaryText, 2);
		BigInteger result = n.multiply(new BigInteger("17"));
		System.out.println(result.toString(2));
	}
} 