package num10103;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int changyeong_score = 100;
		int shangdeok_score = 100;
		
		int n = Integer.parseInt(br.readLine());
		while(n-- > 0)
		{
			String[] scorePanel = br.readLine().split(" ");
			int dice_1_number = Integer.parseInt(scorePanel[0]);
			int dice_2_number = Integer.parseInt(scorePanel[1]);
			if(dice_1_number < dice_2_number) {
				// it means shangdeok is lose this game.
				changyeong_score -= dice_2_number;
			}
			else if(dice_1_number > dice_2_number) {
				// it means changyeong is lose this game.
				shangdeok_score -= dice_1_number;
			}
		}
		
		System.out.println(changyeong_score);
		System.out.println(shangdeok_score);
	}
}
