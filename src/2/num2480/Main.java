package num2480;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] arr = new int[3];
		
		int result = 0;
		int cnt = 0;
		
		for(int i = 0; i < 3; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		for(int i = 0; i < 3; i++)
		{
			if(arr[0] == arr[i]) {
				cnt = arr[0];
			}
			else {
				cnt = arr[1];
			}
		}
		if(arr[0] == arr[1] && arr[0] == arr[2] && arr[1] == arr[2]) {
			result = 10000 + arr[0]*1000;
		}
		else {
			if(arr[0] != arr[1] && arr[0] != arr[2] && arr[1] != arr[2]) {
				result = arr[2] * 100;
			}
			else
			{
				result = 1000 + cnt * 100;
			}
		}
		
		System.out.println(result);
	}
}
