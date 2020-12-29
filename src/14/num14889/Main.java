package num14889;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] arr;
	static int min = Integer.MAX_VALUE;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		visit = new boolean[n];
		for(int i = 0; i < n; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solution(0, 0);
		System.out.println(min);
	}
	public static void solution(int idx, int count)
	{
		if(count == n / 2) {
			diff();
			return;
		}
		for(int i = idx; i < n; i++)
		{
			if(!visit[i]) {
				visit[i] = true;
				solution(i + 1, count + 1);
				visit[i] = false;
			}
		}
	}
	public static void diff()
	{
		int team_start = 0;
		int team_link = 0;
		
		for(int i = 0; i < n - 1; i++)
		{
			for(int j = i + 1; j < n; j++)
			{
				if(visit[i] == true && visit[j] == true) {
					team_start += arr[i][j];
					team_start += arr[j][i];
				}
				else if(visit[i] == false && visit[j] == false)
				{
					team_link += arr[i][j];
					team_link += arr[j][i];
				}
			}
		}
		int val = Math.abs(team_start - team_link);
		
		if(val == 0) {
			System.out.println(val);
			System.exit(0);
		}
		
		min = Math.min(val, min);
	}
}
