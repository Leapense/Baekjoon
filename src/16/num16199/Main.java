package num16199;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] begin = new int[3];
		int[] end = new int[3];
		
		for(int i = 0; i < 3; i++)
		{
			begin[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 3; i++)
		{
			end[i] = Integer.parseInt(st.nextToken());
		}
		
		int age = end[0] - begin[0];
		
		if(begin[1] < end[1] || (begin[1] == end[1] && begin[2] <= end[2]))
		{
			System.out.println(end[0] - begin[0]);
		}
		else {
			System.out.println(end[0] - begin[0] - 1);
		}
		
		System.out.println((end[0] - begin[0]) + 1);
		System.out.println((end[0] - begin[0]));
	}
}
