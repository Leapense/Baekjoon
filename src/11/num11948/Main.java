package num11948;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer[] science = new Integer[4];
		int[] society = new int[2];
		
		for(int i = 0; i < 4; i++)
		{
			science[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 0; i < 2; i++)
		{
			society[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(science, Collections.reverseOrder());
		int sum = 0;
		sum += Math.max(society[0], society[1]);
		for(int i = 0; i < 3; i++)
		{
			sum += science[i];
		}
		
		System.out.println(sum);
	}
}
