package num4493;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(test_case-- > 0)
		{
			int round_stage = Integer.parseInt(br.readLine());
			String[] player_1 = new String[round_stage];
			String[] player_2 = new String[round_stage];
			for(int i = 0; i < round_stage; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				player_1[i] = st.nextToken();
				player_2[i] = st.nextToken();
			}
			//double start = System.currentTimeMillis();
			sb.append(solution(player_1, player_2)).append('\n');
			//double end = System.currentTimeMillis();
			
			//System.err.println("Time: " + (end - start) + "(ms)");
		}
		System.out.println(sb);
	}
	public static String solution(String[] player_1, String[] player_2)
	{
		String answer = "";
		int player_1_point = 0;
		int player_2_point = 0;
		for(int i = 0; i < player_1.length; i++) {
			if(player_1[i].equals("R"))
			{
				if(player_2[i].equals("P"))
				{
					player_2_point += 1;
				}
				else if(player_2[i].equals("S"))
				{
					player_1_point += 1;
				}
			}
			else if(player_1[i].equals("P"))
			{
				if(player_2[i].equals("R"))
				{
					player_1_point += 1;
				}
				else if(player_2[i].equals("S"))
				{
					player_2_point += 1;
				}
			}
			else if(player_1[i].equals("S"))
			{
				if(player_2[i].equals("R"))
				{
					player_2_point += 1;
				}
				else if(player_2[i].equals("P"))
				{
					player_1_point += 1;
				}
			}
		}
		if(player_1_point < player_2_point)
		{
			answer = "Player 2";
		}
		else if(player_1_point == player_2_point)
		{
			answer = "TIE";
		}
		else if(player_1_point > player_2_point)
		{
			answer = "Player 1";
		}
		return answer;
	}
}
