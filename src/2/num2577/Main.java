package num2577;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		int result = a * b * c;
		int count = 0;
		int index = 0;
		String nums = String.valueOf(Integer.toString(result));
		for(int i = 0; i < 10; i++)
		{
			String s = String.valueOf(Integer.toString(i));
			char ch = s.charAt(0);
			int fromIndex = 0;
			while(true)
			{
				index = nums.indexOf(ch, fromIndex);
				if(index == -1) {
					break;
				} else {
					count += 1;
					fromIndex = index + 1;
				}
			}
			System.out.println(count);
			count = 0;
		}
		
	}
}
