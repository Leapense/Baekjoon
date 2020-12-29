package num2750;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] random_array = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			random_array[i] = Integer.parseInt(br.readLine());
		}
		
		basic_sort(random_array);
	}
	static void basic_sort(int[] arr)
	{
		int temp;
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr.length; j++)
			{
				if(arr[i] < arr[j])
				{
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println(arr[i]);
		}
	}
}
