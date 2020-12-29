package num2156;

import java.util.Scanner;

public class Main {
	/***
	 * 입력: wine_cnt
	 * @param wine_cnt 와인잔의 개수
	 * @param wine_streak 술을 2번 연속으로 과감하게 마시지 않기 위한 것
	 * @param wine_price : 1차원 배열로 지정
	 * @param wine_dynamic: 2차원 배열로 지정
	 */
	
	public static int wine_cnt;
	public static int[] wine_price;
	public static int[][] wine_dynamic;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		wine_cnt = sc.nextInt();
		
		/*
		 * 여기서 와인잔의 개수가 1개이면 입력한 값을 그대로 출력한다.
		 */
		if(wine_cnt == 1)
		{
			System.out.println(sc.nextInt());
			return;
		}
		else
		{
			wine_price = new int[wine_cnt];
			wine_dynamic = new int[wine_cnt][3];
			
			for(int i = 0; i < wine_cnt; i++)
			{
				wine_price[i] = sc.nextInt();
				for(int j = 0; j < 3; j++)
				{
					wine_dynamic[i][j] = -1;
				}
			}
			
			int max = getMaxWinePrice(0, 0);
			System.out.println(max);
		}
	}
	public static int getMaxWinePrice(int current_pos, int wine_streak)
	{
		if(current_pos == wine_cnt) {
			return 0;
		}
		
		if(wine_dynamic[current_pos][wine_streak] != -1)
		{
			return wine_dynamic[current_pos][wine_streak];
		}
		
		if(wine_streak == 2)
		{
			wine_dynamic[current_pos][wine_streak] = getMaxWinePrice(current_pos + 1, 0);
		}
		else
		{
			int drink = getMaxWinePrice(current_pos + 1, wine_streak + 1) + wine_price[current_pos];
			int not_drink = getMaxWinePrice(current_pos + 1, 0);
			wine_dynamic[current_pos][wine_streak] = Math.max(drink, not_drink);
		}
		return wine_dynamic[current_pos][wine_streak];
	}
}