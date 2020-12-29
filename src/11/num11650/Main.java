package num11650;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][2];
		for(int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 2; j++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] t1, int[] t2) {
				// TODO Auto-generated method stub
				if(t1[0] == t2[0])
					return t1[1]-t2[1];
				else
					return t1[0]-t2[0];
			}
			
		});
		for(int i = 0; i < n; i++)
		{
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}
	}
}
