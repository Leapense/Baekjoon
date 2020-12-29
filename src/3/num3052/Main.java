package num3052;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[10];
		int count = 0, i, j;
		for(i = 0; i < 10; i++)
		{
			int num = Integer.parseInt(br.readLine());
			arr[i] = num % 42;
			
			for(j = 0; j < i; j++)
			{
				if(arr[i] == arr[j]) break;
			}
			if(i == j) count++;
		}
		
		System.out.println(count);
	}
}
